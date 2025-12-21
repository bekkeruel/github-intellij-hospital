class Patient {
    private int id;
    private String fullName;
    private int age;
    private String phone;
    private MedicalRecord medicalRecord;

    public Patient(int id, String fullName, int age, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void displayInfo() {
        System.out.println("Patient: " + fullName +
                ", Age: " + age +
                ", Phone: " + phone);
    }
}

class Department {
    private int id;
    private String name;
    private String floor;
    private int roomCount;

    public Department(int id, String name, String floor, int roomCount) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.roomCount = roomCount;
    }

    public void displayDepartment() {
        System.out.println("Department: " + name + ", Floor: " + floor);
    }
}

class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private Department department;
    private boolean available;

    public Doctor(int id, String fullName, String specialization, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.department = department;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void assignAppointment() {
        available = false;
    }

    public void finishAppointment() {
        available = true;
    }

    public String getFullName() {
        return fullName;
    }
}

class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;

    public Medicine(int id, String name, String manufacturer, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        }
    }

    public void displayMedicine() {
        System.out.println("Medicine: " + name + ", Quantity: " + quantity);
    }
}

class MedicalRecord {
    private int recordId;
    private Patient patient;
    private String diagnosis;
    private Medicine medicine;
    private String notes;

    public MedicalRecord(int recordId, Patient patient, String diagnosis, Medicine medicine, String notes) {
        this.recordId = recordId;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.medicine = medicine;
        this.notes = notes;
    }

    public void addNotes(String newNotes) {
        notes += " | " + newNotes;
        medicine.reduceQuantity(1);
    }

    public void displayRecord() {
        System.out.println("Medical Record for " + patient.getFullName());
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Notes: " + notes);
    }
}

class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String status;

    public Appointment(int id, Patient patient, Doctor doctor, String date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = "Scheduled";
    }

    public void start() {
        if (doctor.isAvailable()) {
            doctor.assignAppointment();
            status = "In Progress";
        }
    }

    public void complete() {
        doctor.finishAppointment();
        status = "Completed";
    }

    public void displayAppointment() {
        System.out.println("Appointment #" + id +
                " | Patient: " + patient.getFullName() +
                " | Doctor: " + doctor.getFullName() +
                " | Status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {

        Department cardiology = new Department(1, "Cardiology", "2nd Floor", 10);

        Doctor doctor = new Doctor(
                101,
                "Dr. Askar Bekov",
                "Cardiologist",
                cardiology
        );

        Patient patient = new Patient(
                1,
                "Aruzhan Nurtayeva",
                21,
                "+77001112233"
        );

        Medicine medicine = new Medicine(
                501,
                "Aspirin",
                "Bayer",
                500,
                100
        );

        MedicalRecord record = new MedicalRecord(
                1001,
                patient,
                "High blood pressure",
                medicine,
                "Initial diagnosis"
        );

        patient.setMedicalRecord(record);

        Appointment appointment = new Appointment(
                2001,
                patient,
                doctor,
                "2025-12-18"
        );

        // Demonstration
        cardiology.displayDepartment();
        patient.displayInfo();

        appointment.start();
        appointment.displayAppointment();

        record.addNotes("Prescribed Aspirin");
        record.displayRecord();

        appointment.complete();
    }
}