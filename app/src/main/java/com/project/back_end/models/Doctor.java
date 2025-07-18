package com.project.back_end.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a Doctor entity in the clinic system.
 * This class is mapped to a database table and includes fields for doctor's personal
 * and professional information, as well as authentication details.
 */
@Entity // Marks the class as a JPA entity, meaning it represents a table in the database.
public class Doctor {

    @Id // The @Id annotation marks it as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.
    private Long id; // Represents the unique identifier for each doctor.

    @NotNull(message = "Doctor's name cannot be null") // The @NotNull annotation ensures that the doctor's name is required.
    @Size(min = 3, max = 100, message = "Name length must be between 3 and 100 characters") // The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters.
    private String name; // Represents the doctor's name.

    @NotNull(message = "Specialty cannot be null") // The @NotNull annotation ensures that a specialty must be provided.
    @Size(min = 3, max = 50, message = "Specialty name length must be between 3 and 50 characters") // The @Size(min = 3, max = 50) annotation ensures that the specialty name is between 3 and 50 characters long.
    private String specialty; // Represents the medical specialty of the doctor.

    @NotNull(message = "Email cannot be null") // The @NotNull annotation ensures that an email address is required.
    @Email(message = "Email should be a valid email format") // The @Email annotation validates that the email address follows a valid email format (e.g., doctor@example.com).
    private String email; // Represents the doctor's email address.

    @NotNull(message = "Password cannot be null") // The @NotNull annotation ensures that a password must be provided.
    @Size(min = 6, message = "Password must be at least 6 characters long") // The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // The @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) annotation ensures that the password is not serialized in the response (hidden from the frontend).
    private String password; // Represents the doctor's password for login authentication. Note: Store hashed passwords in a real application.

    @NotNull(message = "Phone number cannot be null") // The @NotNull annotation ensures that a phone number must be provided.
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits") // The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.
    private String phone; // Represents the doctor's phone number.

    @ElementCollection // The @ElementCollection annotation ensures that the list of time slots is stored as a separate collection in the database.
    private List<String> availableTimes; // Represents the available times for the doctor in a list of time slots.

    // Default constructor is required by JPA for entity creation.
    public Doctor() {
    }

    /**
     * Parameterized constructor for creating a new Doctor instance.
     *
     * @param name The doctor's name.
     * @param specialty The doctor's medical specialty.
     * @param email The doctor's email address.
     * @param password The doctor's password.
     * @param phone The doctor's phone number.
     * @param availableTimes A list of available time slots for the doctor.
     */
    public Doctor(String name, String specialty, String email, String password, String phone, List<String> availableTimes) {
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.availableTimes = availableTimes;
    }

    // Standard getter and setter methods are provided for all fields.

    /**
     * Retrieves the unique identifier for the doctor.
     * @return The doctor's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the doctor.
     * @param id The doctor's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the doctor's name.
     * @return The doctor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the doctor's name.
     * @param name The doctor's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the doctor's medical specialty.
     * @return The doctor's specialty.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Sets the doctor's medical specialty.
     * @param specialty The doctor's specialty.
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Retrieves the doctor's email address.
     * @return The doctor's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the doctor's email address.
     * @param email The doctor's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the doctor's password hash.
     * @return The doctor's password hash.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the doctor's password hash.
     * @param password The doctor's password hash.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the doctor's phone number.
     * @return The doctor's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the doctor's phone number.
     * @param phone The doctor's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the list of available time slots for the doctor.
     * @return A list of strings representing available times.
     */
    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    /**
     * Sets the list of available time slots for the doctor.
     * @param availableTimes A list of strings representing available times.
     */
    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", specialty='" + specialty + '\'' +
               ", email='" + email + '\'' +
               // Do NOT include password in toString for security reasons
               ", phone='" + phone + '\'' +
               ", availableTimes=" + availableTimes +
               '}';
    }
}
