public class Patient extends Person {

    private String diagnosis;
    private boolean admitted;

    public Patient(int id, String name, int age, String phone,
                   String diagnosis, boolean admitted) {
        super(id, name, age, phone);
        setDiagnosis(diagnosis);
        this.admitted = admitted;
    }

    // validation
    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.isEmpty()) {
            throw new IllegalArgumentException("Diagnosis cannot be empty");
        }
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public boolean isAdmitted() {
        return admitted;
    }

    public void setAdmitted(boolean admitted) {
        this.admitted = admitted;
    }

    // override
    @Override
    public void work() {
        System.out.println("Patient " + name + " is receiving treatment.");
    }

    // 🔥 ВАЖНЫЙ МЕТОД (ИМЕННО ОН)
    public void showDiagnosis() {
        System.out.println("Diagnosis: " + diagnosis);
    }

    public boolean needsTreatment() {
        return diagnosis != null && !diagnosis.isEmpty();
    }
}
