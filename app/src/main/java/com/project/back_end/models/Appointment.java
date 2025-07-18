package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents an Appointment entity in the clinic system.
 * This class is mapped to a database table and includes fields for scheduling
 * consultations between patients and doctors.
 */
@Entity // Marks the class as a JPA entity, meaning it represents a table in the database.
public class Appointment {

    @Id // The @Id annotation marks it as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.
    private Long id;

    @ManyToOne // The @ManyToOne annotation defines the relationship, indicating many appointments can be linked to one doctor.
    @NotNull(message = "Appointment must be associated with a doctor") // The @NotNull annotation ensures that an appointment must be associated with a doctor when created.
    private Doctor doctor; // Represents the doctor assigned to this appointment.

    @ManyToOne // The @ManyToOne annotation defines the relationship, indicating many appointments can be linked to one patient.
    @NotNull(message = "Appointment must be associated with a patient") // The @NotNull annotation ensures that an appointment must be associated with a patient when created.
    private Patient patient; // Represents the patient assigned to this appointment.

    @NotNull(message = "Appointment time cannot be null") // Ensures that the appointment time is not null.
    @Future(message = "Appointment time must be in the future") // The @Future annotation ensures that the appointment time is always in the future when the appointment is created.
    private LocalDateTime appointmentTime; // Represents the date and time when the appointment is scheduled to occur.

    @NotNull(message = "Status cannot be null") // The @NotNull annotation ensures that the status field is not null.
    private int status; // Represents the current status of the appointment. (0 = scheduled, 1 = completed, 2 = cancelled)

    // Default constructor is required by JPA for entity creation.
    public Appointment() {
    }

    /**
     * Parameterized constructor for creating a new Appointment instance.
     *
     * @param doctor The doctor assigned to the appointment.
     * @param patient The patient for the appointment.
     * @param appointmentTime The scheduled date and time for the appointment.
     * @param status The initial status of the appointment.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    /**
     * Retrieves the unique identifier for the appointment.
     * @return The appointment's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the appointment.
     * @param id The appointment's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the doctor assigned to this appointment.
     * @return The Doctor object.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor assigned to this appointment.
     * @param doctor The Doctor object to set.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Retrieves the patient assigned to this appointment.
     * @return The Patient object.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient assigned to this appointment.
     * @param patient The Patient object to set.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Retrieves the scheduled date and time for the appointment.
     * @return The LocalDateTime of the appointment.
     */
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * Sets the scheduled date and time for the appointment.
     * @param appointmentTime The LocalDateTime to set.
     */
    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * Retrieves the current status of the appointment.
     * @return The integer status (0=scheduled, 1=completed, 2=cancelled).
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the current status of the appointment.
     * @param status The integer status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Calculates the estimated end time of the appointment by adding one hour to the start time.
     * This is a transient field and not persisted in the database.
     * @return The LocalDateTime representing the estimated end time.
     */
    public LocalDateTime getEndTime() {
        if (this.appointmentTime == null) {
            return null;
        }
        return this.appointmentTime.plusHours(1);
    }

    /**
     * Extracts only the date part from the appointmentTime field.
     * @return A LocalDate object representing just the date of the scheduled appointment.
     */
    public LocalDate getAppointmentDate() {
        if (this.appointmentTime == null) {
            return null;
        }
        return this.appointmentTime.toLocalDate();
    }

    /**
     * Extracts only the time part from the appointmentTime field.
     * @return A LocalTime object representing just the time of the scheduled appointment.
     */
    public LocalTime getAppointmentTimeOnly() {
        if (this.appointmentTime == null) {
            return null;
        }
        return this.appointmentTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Appointment{" +
               "id=" + id +
               ", doctor=" + (doctor != null ? doctor.getId() : "null") + // Avoid circular reference
               ", patient=" + (patient != null ? patient.getId() : "null") + // Avoid circular reference
               ", appointmentTime=" + appointmentTime +
               ", status=" + status +
               '}';
    }
}
