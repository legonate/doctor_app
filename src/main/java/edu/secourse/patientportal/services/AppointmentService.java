package edu.secourse.patientportal.services;

import edu.secourse.patientportal.models.Appointment;
import edu.secourse.patientportal.models.Doctor;
import edu.secourse.patientportal.models.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();
    private int nextAppointmentId = 1;

    /**
     * Creates a new appointment and adds it to the list
     */
    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        Appointment appointment = new Appointment(nextAppointmentId++, patient, doctor, dateTime, "active");
        appointments.add(appointment);
        return appointment;
    }

    /**
     * Cancels an appointment by its ID
     */
    public boolean cancelAppointment(int appointmentId) {
        for (Appointment appt : appointments) {
            if (appt.getAppointmentId() == appointmentId && appt.getStatus().equals("active")) {
                appt.setStatus("canceled");
                return true;
            }
        }
        return false;
    }

    /**
     * Modifies an appointment’s date and time
     */
    public boolean modifyAppointment(int appointmentId, LocalDateTime newDateTime) {
        for (Appointment appt : appointments) {
            if (appt.getAppointmentId() == appointmentId && appt.getStatus().equals("active")) {
                appt.setDateTime(newDateTime);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all appointments for a patient
     */
    public List<Appointment> getAppointmentsForPatient(Patient patient) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getPatient().equals(patient)) {
                result.add(appt);
            }
        }
        return result;
    }

    /**
     * Retrieves all appointments for a doctor
     */
    public List<Appointment> getAppointmentsForDoctor(Doctor doctor) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getDoctor().equals(doctor)) {
                result.add(appt);
            }
        }
        return result;
    }

    /**
     * Removes an appointment by its ID
     */
    public boolean removeAppointment(int appointmentId) {
        return appointments.removeIf(a -> a.getAppointmentId() == appointmentId);
    }

    /**
     * Returns all appointments in the system
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }
}
