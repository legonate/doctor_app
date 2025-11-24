package edu.secourse.patientportal.services;

import edu.secourse.patientportal.models.Appointment;
import edu.secourse.patientportal.models.Doctor;
import edu.secourse.patientportal.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private Doctor doctor1;
    private Doctor doctor2;
    private Patient patient1;
    private Patient patient2;
    private LocalDateTime dateTime1;
    private LocalDateTime dateTime2;

@BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
        patient1 = new Patient(1, "JohnDoe", "johnDoe@gmail.com", "patient", "John Doe", "pass123");
        patient2 = new Patient(2, "JaneDoe", "janeDoe@gmail.com", "patient", "Jane Doe", "pass124");
        doctor1 = new Doctor(101, "DrBrown", "drBrown@gmail.com", "doctor", "Dr. Brown", "pass125");
        doctor2 = new Doctor(102, "DrSmith", "drSmith@gmail.com", "doctor", "Dr. Smith", "pass126");
        dateTime1 = LocalDateTime.of(2025, 12, 15, 10, 0);
        dateTime2 = LocalDateTime.of(2025, 12, 16, 14, 30);
}
    @Test
    void testCreateAppointment() {
    Appointment appointment = appointmentService.createAppointment(patient1, doctor1, dateTime1);

    assertNotNull(appointment);
    assertEquals(1, appointment.getAppointmentId());
    assertEquals(patient1, appointment.getPatient());
    assertEquals(doctor1, appointment.getDoctor());
    assertEquals(dateTime1, appointment.getDateTime());
    assertEquals("active", appointment.getStatus());
    }

    @Test
    void testCancelAppointment() {
        Appointment appointment = appointmentService.createAppointment(patient1, doctor1, dateTime1);
        int appointmentId = appointment.getAppointmentId();

        boolean result = appointmentService.cancelAppointment(appointmentId);

        assertTrue(result);
        assertEquals("canceled", appointment.getStatus());
    }

    @Test
    void testModifyAppointment() {
        Appointment appointment = appointmentService.createAppointment(patient1, doctor1, dateTime1);
        int appointmentId = appointment.getAppointmentId();

        boolean result = appointmentService.modifyAppointment(appointmentId, dateTime2);

        assertTrue(result);
        assertEquals(dateTime2, appointment.getDateTime());
    }

    @Test
    void testGetAppointmentsForPatient() {
    appointmentService.createAppointment(patient1, doctor1, dateTime1);
    appointmentService.createAppointment(patient1, doctor2, dateTime2);
    appointmentService.createAppointment(patient2, doctor1, dateTime1);

    List<Appointment> patient1Appointments = appointmentService.getAppointmentsForPatient(patient1);

    assertEquals(2,patient1Appointments.size());
    assertTrue(patient1Appointments.stream().allMatch(a -> a.getPatient().equals(patient1)));
    }

    @Test
    void testGetAppointmentsForDoctor() {
        appointmentService.createAppointment(patient1, doctor1, dateTime1);
        appointmentService.createAppointment(patient2, doctor1, dateTime2);
        appointmentService.createAppointment(patient2, doctor2, dateTime1);


        List<Appointment> doctor1Appointments = appointmentService.getAppointmentsForDoctor(doctor1);

        assertEquals(2, doctor1Appointments.size());
        assertTrue(doctor1Appointments.stream().allMatch(a -> a.getDoctor().equals(doctor1)));
    }

    @Test
    void testRemoveAppointment() {
        Appointment appointment = appointmentService.createAppointment(patient1, doctor1, dateTime1);
        int appointmentId = appointment.getAppointmentId();

        boolean result = appointmentService.removeAppointment(appointmentId);

        assertTrue(result);
        assertEquals(0, appointmentService.getAllAppointments().size());
    }

    @Test
    void testGetAllAppointments() {
        appointmentService.createAppointment(patient1, doctor1, dateTime1);
        appointmentService.createAppointment(patient2, doctor2, dateTime2);

        List<Appointment> allAppointments = appointmentService.getAllAppointments();

        assertEquals(2, allAppointments.size());
    }
}