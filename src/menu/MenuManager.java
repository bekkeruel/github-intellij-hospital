package menu;

import database.PersonDAO;
import model.*;
import exceptions.ValidationException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MenuManager {
    private PersonDAO personDAO;
    private Scanner scanner;

    public MenuManager() {
        this.personDAO = new PersonDAO();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getIntInput();

            switch (choice) {
                // PERSON MANAGEMENT (1-7)
                case 1 -> addDoctor();
                case 2 -> addPatient();
                case 3 -> viewAll();
                case 4 -> viewDoctorsOnly();
                case 5 -> viewPatientsOnly();
                case 6 -> updatePerson();
                case 7 -> deletePerson();

                // SEARCH & FILTER (8-10)
                case 8 -> searchByName();
                case 9 -> searchByAgeRange();
                case 10 -> searchByExperience();

                // DEMO & OTHER (11, 0)
                case 11 -> polymorphismDemo();
                case 0 -> {
                    exit();
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please enter 0-11.");
            }

            // Pause between operations
            if (running && choice != 0) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n==========================================");
        System.out.println("      HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("PERSON MANAGEMENT");
        System.out.println("------------------------------------------");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Patient");
        System.out.println("3. View All People");
        System.out.println("4. View Doctors Only");
        System.out.println("5. View Patients Only");
        System.out.println("6. Update Person");
        System.out.println("7. Delete Person");
        System.out.println("------------------------------------------");
        System.out.println("SEARCH & FILTER");
        System.out.println("------------------------------------------");
        System.out.println("8. Search by Name");
        System.out.println("9. Search by Age Range");
        System.out.println("10. Search Doctors by Experience");
        System.out.println("------------------------------------------");
        System.out.println("DEMO & OTHER");
        System.out.println("------------------------------------------");
        System.out.println("11. Polymorphism Demo");
        System.out.println("0. Exit");
        System.out.println("==========================================");
        System.out.print("Choice: ");
    }

    // 1. ADD DOCTOR
    private void addDoctor() {
        System.out.println("\n--- ADD DOCTOR ---");
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = getIntInput();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Specialization: ");
            String spec = scanner.nextLine();
            System.out.print("Experience (years): ");
            int exp = getIntInput();

            Doctor doctor = new Doctor(0, name, age, phone, spec, exp);
            if (personDAO.insertDoctor(doctor)) {
                System.out.println("Doctor added successfully!");
            } else {
                System.out.println("Failed to add doctor");
            }
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 2. ADD PATIENT
    private void addPatient() {
        System.out.println("\n--- ADD PATIENT ---");
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = getIntInput();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Diagnosis: ");
            String diag = scanner.nextLine();
            System.out.print("Admitted? (true/false): ");
            boolean admitted = getBooleanInput();

            Patient patient = new Patient(0, name, age, phone, diag, admitted);
            if (personDAO.insertPatient(patient)) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient");
            }
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input");
        }
    }

    // 3. VIEW ALL
    private void viewAll() {
        System.out.println("\n--- ALL PEOPLE ---");
        List<Person> people = personDAO.getAllPeople();
        if (people.isEmpty()) {
            System.out.println("No people in database");
            return;
        }
        System.out.println("Total: " + people.size() + " people");
        System.out.println("------------------------------------------");
        for (Person p : people) {
            System.out.println(p.getDetails());
            System.out.println("------------------------------------------");
        }
    }

    // 4. VIEW DOCTORS ONLY
    private void viewDoctorsOnly() {
        System.out.println("\n--- ALL DOCTORS ---");
        List<Person> allPeople = personDAO.getAllPeople();
        int doctorCount = 0;

        System.out.println("------------------------------------------");
        for (Person p : allPeople) {
            if (p instanceof Doctor) {
                System.out.println(p.getDetails());
                System.out.println("------------------------------------------");
                doctorCount++;
            }
        }

        if (doctorCount == 0) {
            System.out.println("No doctors in database");
        } else {
            System.out.println("Total: " + doctorCount + " doctors");
        }
    }

    // 5. VIEW PATIENTS ONLY
    private void viewPatientsOnly() {
        System.out.println("\n--- ALL PATIENTS ---");
        List<Person> allPeople = personDAO.getAllPeople();
        int patientCount = 0;

        System.out.println("------------------------------------------");
        for (Person p : allPeople) {
            if (p instanceof Patient) {
                System.out.println(p.getDetails());
                System.out.println("------------------------------------------");
                patientCount++;
            }
        }

        if (patientCount == 0) {
            System.out.println("No patients in database");
        } else {
            System.out.println("Total: " + patientCount + " patients");
        }
    }

    // 6. UPDATE PERSON
    private void updatePerson() {
        System.out.print("\nEnter ID to update: ");
        int id = getIntInput();

        Person person = personDAO.getPersonById(id);
        if (person == null) {
            System.out.println("Person not found with ID: " + id);
            return;
        }

        System.out.println("Current: " + person.getDetails());

        try {
            if (person instanceof Doctor) {
                updateDoctor((Doctor) person);
            } else if (person instanceof Patient) {
                updatePatient((Patient) person);
            }
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateDoctor(Doctor doctor) throws ValidationException {
        System.out.println("\nEnter new values (press Enter to keep current):");

        System.out.print("Name [" + doctor.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = doctor.getName();

        System.out.print("Age [" + doctor.getAge() + "]: ");
        String ageStr = scanner.nextLine();
        int age = ageStr.isEmpty() ? doctor.getAge() : Integer.parseInt(ageStr);

        System.out.print("Phone [" + doctor.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (phone.isEmpty()) phone = doctor.getPhone();

        System.out.print("Specialization [" + doctor.getSpecialization() + "]: ");
        String spec = scanner.nextLine();
        if (spec.isEmpty()) spec = doctor.getSpecialization();

        System.out.print("Experience [" + doctor.getExperienceYears() + "]: ");
        String expStr = scanner.nextLine();
        int exp = expStr.isEmpty() ? doctor.getExperienceYears() : Integer.parseInt(expStr);

        Doctor updated = new Doctor(doctor.getId(), name, age, phone, spec, exp);

        if (personDAO.updateDoctor(updated)) {
            System.out.println("Doctor updated successfully!");
        } else {
            System.out.println("Update failed");
        }
    }

    private void updatePatient(Patient patient) throws ValidationException {
        System.out.println("\nEnter new values (press Enter to keep current):");

        System.out.print("Name [" + patient.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = patient.getName();

        System.out.print("Age [" + patient.getAge() + "]: ");
        String ageStr = scanner.nextLine();
        int age = ageStr.isEmpty() ? patient.getAge() : Integer.parseInt(ageStr);

        System.out.print("Phone [" + patient.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (phone.isEmpty()) phone = patient.getPhone();

        System.out.print("Diagnosis [" + patient.getDiagnosis() + "]: ");
        String diag = scanner.nextLine();
        if (diag.isEmpty()) diag = patient.getDiagnosis();

        System.out.print("Admitted [" + patient.isAdmitted() + "]: ");
        String admStr = scanner.nextLine();
        boolean admitted = admStr.isEmpty() ? patient.isAdmitted() : getBooleanInput(admStr);

        Patient updated = new Patient(patient.getId(), name, age, phone, diag, admitted);

        if (personDAO.updatePatient(updated)) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Update failed");
        }
    }

    // 7. DELETE PERSON
    private void deletePerson() {
        System.out.print("\nEnter ID to delete: ");
        int id = getIntInput();

        Person person = personDAO.getPersonById(id);
        if (person == null) {
            System.out.println("Person not found with ID: " + id);
            return;
        }

        System.out.println("Will delete: " + person.getDetails());
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if ("yes".equalsIgnoreCase(confirm) || "y".equalsIgnoreCase(confirm)) {
            if (personDAO.deletePerson(id)) {
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("Delete failed");
            }
        } else {
            System.out.println("Delete cancelled");
        }
    }

    // 8. SEARCH BY NAME
    private void searchByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine();
        List<Person> results = personDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("No results found for: " + name);
            return;
        }
        System.out.println("Found " + results.size() + " results:");
        System.out.println("------------------------------------------");
        for (Person p : results) {
            System.out.println(p.getDetails());
            System.out.println("------------------------------------------");
        }
    }

    // 9. SEARCH BY AGE RANGE
    private void searchByAgeRange() {
        System.out.println("\n--- SEARCH BY AGE RANGE ---");
        System.out.print("Minimum age: ");
        int minAge = getIntInput();
        System.out.print("Maximum age: ");
        int maxAge = getIntInput();

        if (minAge > maxAge) {
            System.out.println("Error: Minimum age cannot be greater than maximum age");
            return;
        }

        List<Person> results = personDAO.searchByAgeRange(minAge, maxAge);

        if (results.isEmpty()) {
            System.out.println("No people found in age range " + minAge + "-" + maxAge);
        } else {
            System.out.println("Found " + results.size() + " people:");
            System.out.println("------------------------------------------");
            for (Person p : results) {
                System.out.println(p.getDetails());
                System.out.println("------------------------------------------");
            }
        }
    }

    // 10. SEARCH DOCTORS BY EXPERIENCE
    private void searchByExperience() {
        System.out.println("\n--- SEARCH DOCTORS BY EXPERIENCE ---");
        System.out.print("Minimum experience (years): ");
        int minExperience = getIntInput();

        if (minExperience < 0) {
            System.out.println("Error: Experience cannot be negative");
            return;
        }

        List<Doctor> doctors = personDAO.searchDoctorsByExperience(minExperience);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found with " + minExperience + "+ years experience");
        } else {
            System.out.println("Found " + doctors.size() + " doctors:");
            System.out.println("------------------------------------------");
            for (Doctor d : doctors) {
                System.out.println(d.getDetails());
                System.out.println("------------------------------------------");
            }
        }
    }

    // 11. POLYMORPHISM DEMO
    private void polymorphismDemo() {
        System.out.println("\n==========================================");
        System.out.println("      POLYMORPHISM DEMONSTRATION");
        System.out.println("==========================================");

        List<Person> people = personDAO.getAllPeople();

        if (people.isEmpty()) {
            System.out.println("No people in database. Add some doctors/patients first!");
            return;
        }

        // No ArrayLists needed - just count and display
        int doctorCount = 0;
        int patientCount = 0;
        int treatableCount = 0;

        // DEMO 1: Polymorphic work() method
        System.out.println("\nDEMO 1: Polymorphic work() method");
        System.out.println("------------------------------------------");
        for (Person person : people) {
            System.out.print(person.getName() + " (" + person.getRole() + "): ");
            person.work();

            // Count types
            if (person instanceof Doctor) {
                doctorCount++;
                treatableCount++;
            } else if (person instanceof Patient) {
                patientCount++;
            }
        }

        // Continue with rest of method without creating ArrayLists...
    }

    // 0. EXIT
    private void exit() {
        System.out.println("\n==========================================");
        System.out.println("  Thank you for using");
        System.out.println("  Hospital Management System");
        System.out.println("==========================================");
    }

    // HELPER METHODS
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private boolean getBooleanInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y") || input.equals("true")) {
                return true;
            } else if (input.equals("no") || input.equals("n") || input.equals("false")) {
                return false;
            }
            System.out.print("Please enter 'yes' or 'no': ");
        }
    }

    private boolean getBooleanInput(String input) {
        input = input.trim().toLowerCase();
        return input.equals("yes") || input.equals("y") || input.equals("true");
    }
}