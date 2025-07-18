package com.project.back_end.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a Prescription document in the clinic system's MongoDB.
 * This class is mapped to the "prescriptions" collection in MongoDB,
 * providing a flexible schema for prescription details.
 */
@Document(collection = "prescriptions") // Marks the class as a MongoDB document and specifies the collection name.
public class Prescription {

    @Id // The @Id annotation marks it as the primary key in the MongoDB collection.
    private String id; // Represents the unique identifier for each prescription. The id is of type String, which is commonly used for MongoDB's ObjectId.

    @NotNull(message = "Patient name cannot be null") // Ensures that the patient name is required.
    @Size(min = 3, max = 100, message = "Patient name length must be between 3 and 100 characters") // Ensures that the name length is between 3 and 100 characters.
    private String patientName; // Represents the name of the patient receiving the prescription.

    @NotNull(message = "Appointment ID cannot be null") // Ensures that the appointment ID is required for the prescription.
    private Long appointmentId; // Represents the ID of the associated appointment where the prescription was given (links to MySQL Appointment entity).

    @NotNull(message = "Medication cannot be null") // Ensures that the medication name is required.
    @Size(min = 3, max = 100, message = "Medication name length must be between 3 and 100 characters") // Ensures that the medication name is between 3 and 100 characters.
    private String medication; // Represents the medication prescribed to the patient.

    @NotNull(message = "Dosage cannot be null") // Ensures that the dosage information is provided.
    private String dosage; // Represents the dosage information for the prescribed medication.

    @Size(max = 200, message = "Doctor's notes cannot exceed 200 characters") // Ensures that the doctor's notes do not exceed 200 characters.
    private String doctorNotes; // Represents any additional notes or instructions from the doctor.

    // Default constructor is required by Spring Data MongoDB.
    public Prescription() {
    }

    /**
     * Parameterized constructor for creating a new Prescription instance.
     *
     * @param patientName The name of the patient.
     * @param appointmentId The ID of the associated appointment.
     * @param medication The prescribed medication.
     * @param dosage The dosage information.
     * @param doctorNotes Any additional notes from the doctor.
     */
    public Prescription(String patientName, Long appointmentId, String medication, String dosage, String doctorNotes) {
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
    }

    // Standard getter and setter methods for all fields.

    /**
     * Retrieves the unique identifier for the prescription.
     * @return The prescription's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the prescription.
     * @param id The prescription's ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the patient for this prescription.
     * @return The patient's name.
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets the name of the patient for this prescription.
     * @param patientName The patient's name.
     */
    public void PatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * Retrieves the ID of the associated appointment.
     * @return The appointment ID.
     */
    public Long getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the ID of the associated appointment.
     * @param appointmentId The appointment ID.
     */
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieves the prescribed medication.
     * @return The medication name.
     */
    public String getMedication() {
        return medication;
    }

    /**
     * Sets the prescribed medication.
     * @param medication The medication name.
     */
    public void setMedication(String medication) {
        this.medication = medication;
    }

    /**
     * Retrieves the dosage information for the medication.
     * @return The dosage.
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage information for the medication.
     * @param dosage The dosage.
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Retrieves any additional notes from the doctor.
     * @return The doctor's notes.
     */
    public String getDoctorNotes() {
        return doctorNotes;
    }

    /**
     * Sets any additional notes from the doctor.
     * @param doctorNotes The doctor's notes.
     */
    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    @Override
    public String toString() {
        return "Prescription{" +
               "id='" + id + '\'' +
               ", patientName='" + patientName + '\'' +
               ", appointmentId=" + appointmentId +
               ", medication='" + medication + '\'' +
               ", dosage='" + dosage + '\'' +
               ", doctorNotes='" + doctorNotes + '\'' +
               '}';
    }
}
