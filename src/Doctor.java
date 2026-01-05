public class Doctor extends Person {

    private String specialization;
    private int experienceYears;

    public Doctor(int id, String name, int age, String phone,
                  String specialization, int experienceYears) {
        super(id, name, age, phone);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients.");
    }

    public void treatPatient() {
        System.out.println("Doctor " + name + " treats a patient.");
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
}