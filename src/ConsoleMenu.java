import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    private static ArrayList<Person> people = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Add Appointment");
            System.out.println("4. View All People");
            System.out.println("5. Demonstrate Polymorphism");
            System.out.println("6. Show Specific Information");
            System.out.println("7. View All Appointments");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> addPatient();
                case 3 -> addAppointment();
                case 4 -> viewAllPeople();
                case 5 -> demonstratePolymorphism();
                case 6 -> showSpecificInfo();
                case 7 -> viewAllAppointments();
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void addDoctor() {
        System.out.println("\n=== ADD NEW DOCTOR ===");
        try {
            System.out.print("ID: ");
            int id = getIntInput();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = getIntInput();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();

            System.out.print("Years of Experience: ");
            int experience = getIntInput();

            Doctor doctor = new Doctor(id, name, age, phone, specialization, experience);
            people.add(doctor);
            System.out.println("✅ Doctor added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void addPatient() {
        System.out.println("\n=== ADD NEW PATIENT ===");
        try {
            System.out.print("ID: ");
            int id = getIntInput();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = getIntInput();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Diagnosis: ");
            String diagnosis = scanner.nextLine();

            System.out.print("Admitted? (true/false): ");
            boolean admitted = Boolean.parseBoolean(scanner.nextLine());

            Patient patient = new Patient(id, name, age, phone, diagnosis, admitted);
            people.add(patient);
            System.out.println("✅ Patient added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void addAppointment() {
        System.out.println("\n=== ADD NEW APPOINTMENT ===");

        if (people.isEmpty()) {
            System.out.println("❌ No doctors or patients available. Please add some first.");
            return;
        }

        // Show available doctors
        System.out.println("Available Doctors:");
        int doctorIndex = 1;
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (Person p : people) {
            if (p instanceof Doctor) {
                Doctor d = (Doctor) p;
                System.out.println(doctorIndex + ". Dr. " + d.getName() +
                        " - " + d.getSpecialization() +
                        " [EXP: " + d.getExperienceYears() + " years]");
                doctors.add(d);
                doctorIndex++;
            }
        }

        if (doctors.isEmpty()) {
            System.out.println("❌ No doctors available. Please add a doctor first.");
            return;
        }

        System.out.print("Select doctor (number): ");
        int docChoice = getIntInput();
        if (docChoice < 1 || docChoice > doctors.size()) {
            System.out.println("❌ Invalid doctor selection.");
            return;
        }
        Doctor selectedDoctor = doctors.get(docChoice - 1);

        // Show available patients
        System.out.println("\nAvailable Patients:");
        int patientIndex = 1;
        ArrayList<Patient> patients = new ArrayList<>();
        for (Person p : people) {
            if (p instanceof Patient) {
                Patient pat = (Patient) p;
                System.out.println(patientIndex + ". " + pat.getName() +
                        " - Diagnosis: " + pat.getDiagnosis() +
                        " [Admitted: " + pat.isAdmitted() + "]");
                patients.add(pat);
                patientIndex++;
            }
        }

        if (patients.isEmpty()) {
            System.out.println("❌ No patients available. Please add a patient first.");
            return;
        }

        System.out.print("Select patient (number): ");
        int patChoice = getIntInput();
        if (patChoice < 1 || patChoice > patients.size()) {
            System.out.println("❌ Invalid patient selection.");
            return;
        }
        Patient selectedPatient = patients.get(patChoice - 1);

        System.out.print("Appointment Date (e.g., 2024-01-15): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(selectedDoctor, selectedPatient, date);
        appointments.add(appointment);
        System.out.println("✅ Appointment scheduled successfully!");
    }

    private static void viewAllPeople() {
        System.out.println("\n=== ALL PEOPLE ===");
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
            return;
        }

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            String typeBadge = "";

            if (p instanceof Doctor) {
                typeBadge = "[DOCTOR]";
            } else if (p instanceof Patient) {
                typeBadge = "[PATIENT]";
            } else {
                typeBadge = "[PERSON]";
            }

            System.out.println((i + 1) + ". " + typeBadge + " " + p.getName() +
                    " (ID: " + p.getId() + ", Age: " + p.getAge() + ")");
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n=== DEMONSTRATING POLYMORPHISM ===");
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
            return;
        }

        System.out.println("Each person performing their work:");
        System.out.println("-----------------------------------");
        for (Person p : people) {
            System.out.print(p.getName() + " (" + p.getRole() + "): ");
            p.work();
        }
    }

    private static void showSpecificInfo() {
        System.out.println("\n=== SPECIFIC INFORMATION (using instanceof) ===");
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
            return;
        }

        for (Person p : people) {
            System.out.println("\n--- " + p.getName() + " ---");

            if (p instanceof Doctor) {
                Doctor d = (Doctor) p;
                System.out.println("Role: [DOCTOR]");
                System.out.println("Specialization: " + d.getSpecialization());
                System.out.println("Experience: " + d.getExperienceYears() + " years");
                System.out.println("Experienced (>5 years): " + (d.isExperienced() ? "✅ Yes" : "❌ No"));
                System.out.println("Specific Action: ");
                d.treatPatient();

            } else if (p instanceof Patient) {
                Patient pat = (Patient) p;
                System.out.println("Role: [PATIENT]");
                System.out.println("Diagnosis: " + pat.getDiagnosis());
                System.out.println("Admitted to hospital: " + (pat.isAdmitted() ? "🏥 Yes" : "🏠 No"));
                System.out.println("Needs treatment: " + (pat.needsTreatment() ? "✅ Yes" : "❌ No"));
                System.out.println("Specific Action: ");
                pat.showDiagnosis();

            } else {
                System.out.println("Role: [PERSON]");
                System.out.println("No additional specific information.");
            }
        }
    }

    private static void viewAllAppointments() {
        System.out.println("\n=== ALL APPOINTMENTS ===");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);
            System.out.println((i + 1) + ". Appointment on " + app.getDate());
            System.out.println("   Doctor: " + app.getDoctor().getName() +
                    " (" + app.getDoctor().getSpecialization() + ")");
            System.out.println("   Patient: " + app.getPatient().getName() +
                    " - " + app.getPatient().getDiagnosis());
            System.out.println("   Status: " + app.getStatus());
            System.out.println("   Completed: " + (app.isCompleted() ? "✅" : "⏳"));
            System.out.println();
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
