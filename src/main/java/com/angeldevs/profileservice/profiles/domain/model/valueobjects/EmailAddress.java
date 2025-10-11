package com.angeldevs.profileservice.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * EmailAddress value object
 *
 * @summary
 * EmailAddress represents a valid email address.
 * This value object ensures email validation and provides utility methods.
 *
 * @since 1.0
 */
@Embeddable
public record EmailAddress(String address) {

    /**
     * Default constructor for EmailAddress
     *
     * @param address Email address string
     * @throws IllegalArgumentException if address is null, blank, exceeds length limits, or has invalid format
     */
    public EmailAddress {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or blank");
        }
        if (address.length() > 255) {
            throw new IllegalArgumentException("Email address cannot exceed 255 characters");
        }
        if (!isValidEmail(address)) {
            throw new IllegalArgumentException("Email address format is invalid");
        }
    }

    /**
     * Validate email format
     *
     * @param email Email address to validate
     * @return true if email format is valid, false otherwise
     */
    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    }

    /**
     * Get the domain part of the email
     *
     * @return Domain part of the email address
     */
    public String getDomain() {
        return address.substring(address.indexOf("@") + 1);
    }

    /**
     * Get the local part of the email
     *
     * @return Local part of the email address (before @)
     */
    public String getLocalPart() {
        return address.substring(0, address.indexOf("@"));
    }
}