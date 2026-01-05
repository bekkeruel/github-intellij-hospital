public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String status;

    public Appointment(Doctor doctor, Patient patient, String date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.status = "Scheduled";
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void completeAppointment() {
        status = "Completed";
    }

    public boolean isCompleted() {
        return status.equals("Completed");
    }
}