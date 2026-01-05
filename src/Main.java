import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Person> people = new ArrayList<>();

        Doctor doctor = new Doctor(
                1,
                "Askar",
                45,
                "87770001122",
                "Therapist",
                10
        );

        Patient patient = new Patient(
                2,
                "Aruzhan",
                20,
                "87015554433",
                "Flu",
                true
        );

        people.add(doctor);
        people.add(patient);

        System.out.println("=== POLYMORPHISM ===");
        for (Person p : people) {
            System.out.println(p.getRole());
            p.work();
        }

        System.out.println("\n=== INSTANCEOF ===");
        for (Person p : people) {
            if (p instanceof Doctor) {
                Doctor d = (Doctor) p;
                d.treatPatient();
                System.out.println("Experienced: " + d.isExperienced());
            } else if (p instanceof Patient) {
                Patient pat = (Patient) p;
                pat.showDiagnosis();
            }
        }

        Appointment appointment = new Appointment(
                doctor,
                patient,
                "2026-01-05"
        );

        appointment.completeAppointment();
        System.out.println("\nAppointment completed: " + appointment.isCompleted());
    }
}