import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Person> people = new ArrayList<>();

        // parent object
        people.add(new Person(
                1,
                "Alex",
                30,
                "+77000000001"
        ));

        // child objects (POLYMORPHISM)
        people.add(new Patient(
                2,
                "Aruzhan",
                21,
                "+77001112233",
                "Flu"
        ));

        people.add(new Doctor(
                3,
                "Dr. Askar",
                45,
                "+77009998877",
                "Therapist",
                10
        ));

        // POLYMORPHIC BEHAVIOR
        System.out.println("=== POLYMORPHISM DEMO ===");
        for (Person p : people) {
            System.out.println(p.getRole());
            p.work(); // same method, different behavior
        }

        // instanceof + downcasting
        System.out.println("\n=== INSTANCEOF DEMO ===");
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
    }
}