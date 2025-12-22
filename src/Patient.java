public class Patient {
    private int id;
    private String fullName;
    private int age;
    private String phone;
    private MedicalRecord medicalRecord;

    public Patient(int id, String fullName, int age, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void displayInfo() {
        System.out.println("Patient: " + fullName + ", Age: " + age + ", Phone: " + phone);
    }
}
