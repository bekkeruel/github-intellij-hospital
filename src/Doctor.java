public class Doctor extends Person {
    private String specialization;
    private int experienceYears;

    public Doctor(int id, String name, int age, String phone,
                  String specialization, int experienceYears) {
        super(id, name, age, phone);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience cannot be negative");
        }
        this.experienceYears = experienceYears;
    }

    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients.");
    }

    @Override
    public String getRole() {
        return "Doctor (" + specialization + ")";
    }

    public void treatPatient() {
        System.out.println("Doctor " + name + " treats a patient.");
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
}