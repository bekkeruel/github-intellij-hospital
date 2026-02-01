package model;

import exceptions.ValidationException;

public class Doctor extends Person implements Treatable {
    private String specialization;
    private int experienceYears;

    public Doctor(int id, String name, int age, String phone,
                  String specialization, int experienceYears) throws ValidationException {
        super(id, name, age, phone);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) throws ValidationException {
        if (specialization == null || specialization.isEmpty()) {
            throw new ValidationException("Specialization cannot be empty");
        }
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) throws ValidationException {
        if (experienceYears < 0) {
            throw new ValidationException("Experience cannot be negative");
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

    @Override
    public String getDetails() {
        return "Doctor: " + name + ", Specialization: " + specialization +
                ", Experience: " + experienceYears + " years";
    }


    public void treatPatient() {
        System.out.println("Doctor " + name + " treats a patient.");
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
    @Override
    public void performTreatment() {
        System.out.println("Dr. " + name + " is performing medical treatment.");
    }

    @Override
    public String getTreatmentDetails() {
        return "Treatment by " + name + " (" + specialization + ")";
    }
}
