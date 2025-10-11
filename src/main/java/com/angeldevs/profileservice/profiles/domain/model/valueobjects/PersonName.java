package com.angeldevs.profileservice.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * PersonName value object
 *
 * @summary
 * PersonName represents a person's name with first and last name components.
 * This value object ensures name validation and provides utility methods.
 *
 * @since 1.0
 */
@Embeddable
public record PersonName(String firstName, String lastName) {

    /**
     * Default constructor for PersonName
     *
     * @param firstName First name of the person
     * @param lastName Last name of the person
     * @throws IllegalArgumentException if firstName or lastName is null, blank, or exceeds length limits
     */
    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        if (firstName.length() > 50) {
            throw new IllegalArgumentException("First name cannot exceed 50 characters");
        }
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Last name cannot exceed 50 characters");
        }
    }

    /**
     * Get the full name
     *
     * @return Full name combining first and last name
     */
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Get the initials
     *
     * @return Initials of the person
     */
    public String getInitials() {
        return String.format("%c%c", firstName.charAt(0), lastName.charAt(0)).toUpperCase();
    }
}