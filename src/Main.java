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