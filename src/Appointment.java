public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String status;

    public Appointment(int id, Patient patient, Doctor doctor, String date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = "Scheduled";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void startAppointment() {
        if (doctor.isAvailable()) {
            doctor.assignAppointment();
            status = "In Progress";
        }
    }

    public void completeAppointment() {
        doctor.finishAppointment();
        status = "Completed";
    }
}
