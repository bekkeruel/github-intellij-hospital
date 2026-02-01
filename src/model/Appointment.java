package model;

import exceptions.ValidationException;

public class Appointment {
    private int id; // ← Добавить поле ID
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String status;

    public Appointment(Doctor doctor, Patient patient, String date) throws ValidationException {
        setDoctor(doctor);
        setPatient(patient);
        setDate(date);
        this.status = "Scheduled";
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; } // ← ДОБАВИТЬ ЭТОТ МЕТОД!

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) throws ValidationException {
        if (doctor == null) throw new ValidationException("Doctor cannot be null");
        this.doctor = doctor;
    }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) throws ValidationException {
        if (patient == null) throw new ValidationException("Patient cannot be null");
        this.patient = patient;
    }

    public String getDate() { return date; }
    public void setDate(String date) throws ValidationException {
        if (date == null || date.isEmpty()) throw new ValidationException("Date cannot be empty");
        this.date = date;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void completeAppointment() {
        status = "Completed";
    }

    public boolean isCompleted() {
        return status.equals("Completed");
    }
}