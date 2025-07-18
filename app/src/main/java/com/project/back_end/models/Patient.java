package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Patient entity in the clinic system.
 * This class is mapped to a database table and includes fields for patient's personal
 * information and authentication details.
 */
@Entity // Marks the class as a JPA entity, meaning it represents a table in the database.
public class Patient {

    @Id // The @Id annotation marks it as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.
    private Long id; // Represents the unique identifier for each patient.

    @NotNull(message = "Patient's name cannot be null") // The @NotNull annotation ensures that the patient's name is required.
    @Size(min = 3, max = 100, message = "Name length must be between 3 and 100 characters") // The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters.
    private String name; // Represents the patient's full name.

    @NotNull(message = "Email cannot be null") // The @NotNull annotation ensures that an email address must be provided.
    @Email(message = "Email should be a valid email format") // The @Email annotation validates that the email address follows a valid email format (e.g., patient@example.com).
    private String email; // Represents the patient's email address.

    @NotNull(message = "Password cannot be null") // The @NotNull annotation ensures that a password must be provided.
    @Size(min = 6, message = "Password must be at least 6 characters long") // The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Ensures the password is not serialized in JSON responses.
    private String password; // Represents the patient's password for login authentication. Note: Store hashed passwords in a real application.

    @NotNull(message = "Phone number cannot be null") // The @NotNull annotation ensures that a phone number must be provided.
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits") // The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.
    private String phone; // Represents the patient's phone number.

    @NotNull(message = "Address cannot be null") // The @NotNull annotation ensures that the address must be provided.
    @Size(max = 255, message = "Address length cannot exceed 255 characters") // The @Size(max = 255) annotation ensures that the address does not exceed 255 characters in length.
    private String address; // Represents the patient's address.

    // Default constructor is required by JPA for entity creation.
    public Patient() {
    }

    /**
     * Parameterized constructor for creating a new Patient instance.
     *
     * @param name The patient's full name.
     * @param email The patient's email address.
     * @param password The patient's password.
     * @param phone The patient's phone number.
     * @param address The patient's address.
     */
    public Patient(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Standard getter and setter methods are provided for all fields.

    /**
     * Retrieves the unique identifier for the patient.
     * @return The patient's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the patient.
     * @param id The patient's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the patient's full name.
     * @return The patient's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the patient's full name.
     * @param name The patient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the patient's email address.
     * @return The patient's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the patient's email address.
     * @param email The patient's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the patient's password hash.
     * @return The patient's password hash.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the patient's password hash.
     * @param password The patient's password hash.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the patient's phone number.
     * @return The patient's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the patient's phone number.
     * @param phone The patient's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the patient's address.
     * @return The patient's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the patient's address.
     * @param address The patient's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               // Do NOT include password in toString for security reasons
               ", phone='" + phone + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}