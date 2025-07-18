package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an Admin entity in the clinic system.
 * This class is mapped to a database table and includes fields for authentication.
 */
@Entity // Marks this class as a JPA entity, meaning it will be mapped to a database table.
public class Admin {

    @Id // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key to be auto-incremented by the database.
    private Long id;

    @NotNull(message = "username cannot be null") // Ensures that the username field cannot be null.
    private String username;

    @NotNull(message = "password cannot be null") // Ensures that the password field cannot be null.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Hides the password from JSON responses but allows it in requests.
    private String password; // Note: In a real application, store hashed passwords, not plain text.

    // Default constructor is required by JPA for entity creation.
    public Admin() {
    }

    /**
     * Parameterized constructor for creating a new Admin instance.
     *
     * @param username The admin's unique username.
     * @param password The admin's password (will be hashed before storage in a real app).
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the unique identifier of the admin.
     *
     * @return The admin's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the admin.
     * This is typically managed by the database (auto-increment).
     *
     * @param id The admin's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the admin.
     *
     * @return The admin's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the admin.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password hash of the admin.
     * Note: This getter is for internal use (e.g., authentication logic).
     * The @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) annotation
     * prevents it from being serialized into JSON responses.
     *
     * @return The admin's password hash.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password hash of the admin.
     *
     * @param password The new password hash.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
               "id=" + id +
               ", username='" + username + '\'' +
               // Do NOT include password in toString for security reasons
               '}';
    }
}