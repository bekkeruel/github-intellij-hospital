public class Doctor extends Person {

    private String specialization;
    private int experienceYears;

    public Doctor(int id, String name, int age, String phone,
                  String specialization, int experienceYears) {
        super(id, name, age, phone);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    // getters & setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    // overridden methods
    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients.");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    // logic methods
    public void treatPatient() {
        System.out.println("Doctor " + name + " treats a patient.");
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
}