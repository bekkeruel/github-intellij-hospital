public class Patient extends Person {
    private String diagnosis;

    public Patient(int id, String name, int age, String phone, String diagnosis) {
        super(id, name, age, phone); // super — ПЕРВАЯ СТРОКА
        this.diagnosis = diagnosis;
    }

    // getter & setter
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // overridden methods
    @Override
    public void work() {
        System.out.println("Patient " + name + " is receiving treatment.");
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    // unique methods
    public void showDiagnosis() {
        System.out.println("Diagnosis: " + diagnosis);
    }

    public boolean needsTreatment() {
        return diagnosis != null && !diagnosis.isEmpty();
    }
}