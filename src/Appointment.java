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

    public void start() {
        if (doctor.isAvailable()) {
            doctor.assignAppointment();
            status = "In Progress";
        }
    }

    public void complete() {
        doctor.finishAppointment();
        status = "Completed";
    }

    public void displayAppointment() {
        System.out.println("Appointment #" + id +
                " | Patient: " + patient.getFullName() +
                " | Doctor: " + doctor.getFullName() +
                " | Status: " + status);
    }
}
