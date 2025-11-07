package edu.secourse.patientportal.models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String status;
    /**
     * constructs new appointment
     *
     * @param appointmentId the unique identifier for this appointment
     * @param patient the patient associated with this appointment
     * @param doctor the doctor associated with this appointment
     * @param dateTime the scheduled date and time for the appointment
     * @param status the current status of the appointment
     */
    public Appointment(int appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    /**
     * @return the appointmentID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment ID
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date/time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the date/time
     */
    public void setStatus(String status) {
        this.status = status;
    }
}