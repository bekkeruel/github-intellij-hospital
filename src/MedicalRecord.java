public class MedicalRecord {
    private int recordId;
    private Patient patient;
    private String diagnosis;
    private Medicine medicine;
    private String notes;

    public MedicalRecord(int recordId, Patient patient, String diagnosis, Medicine medicine, String notes) {
        this.recordId = recordId;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.medicine = medicine;
        this.notes = notes;
    }

    public void addNotes(String newNotes) {
        notes += " | " + newNotes;
    }

    public void displayRecord() {
        System.out.println("Medical Record for " + patient.getFullName());
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Notes: " + notes);
    }
}
