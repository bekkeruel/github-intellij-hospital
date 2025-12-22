public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private Department department;
    private boolean available;

    public Doctor(int id, String fullName, String specialization, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.department = department;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void assignAppointment() {
        available = false;
    }

    public void finishAppointment() {
        available = true;
    }

    public String getFullName() {
        return fullName;
    }
}
