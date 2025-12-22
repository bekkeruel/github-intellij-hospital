public class Main {
    public static void main(String[] args) {

        Patient patient = new Patient(
                1,
                "Aruzhan Nurtayeva",
                21,
                "+77001112233"
        );

        Doctor doctor = new Doctor(
                101,
                "Dr. Askar Bekov",
                "Therapist"
        );

        Appointment appointment = new Appointment(
                1001,
                patient,
                doctor,
                "2025-12-18"
        );

        patient.displayInfo();

        appointment.startAppointment();
        System.out.println("Status: " + appointment.getStatus());

        appointment.completeAppointment();
        System.out.println("Status: " + appointment.getStatus());

    }
}