package com.angeldevs.profileservice.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * StreetAddress value object
 *
 * @summary
 * StreetAddress represents a complete street address with all components.
 * This value object ensures address validation and provides utility methods.
 *
 * @since 1.0
 */
@Embeddable
public record StreetAddress(String street, String number, String city, String postalCode, String country) {

    /**
     * Default constructor for StreetAddress
     *
     * @param street Street name
     * @param number Street number
     * @param city City name
     * @param postalCode Postal code
     * @param country Country name
     * @throws IllegalArgumentException if any field exceeds length limits
     */
    public StreetAddress {
        if (street != null && street.length() > 255) {
            throw new IllegalArgumentException("Street cannot exceed 255 characters");
        }
        if (number != null && number.length() > 10) {
            throw new IllegalArgumentException("Street number cannot exceed 10 characters");
        }
        if (city != null && city.length() > 100) {
            throw new IllegalArgumentException("City cannot exceed 100 characters");
        }
        if (postalCode != null && postalCode.length() > 20) {
            throw new IllegalArgumentException("Postal code cannot exceed 20 characters");
        }
        if (country != null && country.length() > 100) {
            throw new IllegalArgumentException("Country cannot exceed 100 characters");
        }
    }

    /**
     * Get the full address as a formatted string
     *
     * @return Complete address as a single string
     */
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();

        if (street != null && !street.isBlank()) {
            fullAddress.append(street);
        }

        if (number != null && !number.isBlank()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(" ");
            }
            fullAddress.append(number);
        }

        if (city != null && !city.isBlank()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(", ");
            }
            fullAddress.append(city);
        }

        if (postalCode != null && !postalCode.isBlank()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(" ");
            }
            fullAddress.append(postalCode);
        }

        if (country != null && !country.isBlank()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(", ");
            }
            fullAddress.append(country);
        }

        return fullAddress.toString();
    }

    /**
     * Check if the address is empty
     *
     * @return true if all address components are null or empty
     */
    public boolean isEmpty() {
        return (street == null || street.isBlank()) &&
                (number == null || number.isBlank()) &&
                (city == null || city.isBlank()) &&
                (postalCode == null || postalCode.isBlank()) &&
                (country == null || country.isBlank());
    }
}