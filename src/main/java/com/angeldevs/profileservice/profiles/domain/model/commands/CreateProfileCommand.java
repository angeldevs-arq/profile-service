package com.angeldevs.profileservice.profiles.domain.model.commands;


import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;

/**
 * CreateProfileCommand
 *
 * @summary
 * Command to create a new profile with personal information.
 * Contains all necessary data for profile creation.
 *
 * @param firstName First name of the person
 * @param lastName Last name of the person
 * @param email Email address
 * @param street Street address
 * @param number Street number
 * @param city City
 * @param postalCode Postal code
 * @param country Country
 * @param type Profile type
 * @since 1.0
 */
public record CreateProfileCommand(
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country,
        String profileImageUrl,
        String profileImagePublicId,
        ProfileType type,
        Long userId
) {
    /**
     * Constructor with validation
     *
     * @param firstName First name of the person
     * @param lastName Last name of the person
     * @param email Email address
     * @param street Street address
     * @param number Street number
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     * @param type Profile type
     * @throws IllegalArgumentException if required fields are null or empty
     */
    public CreateProfileCommand {
        if (firstName == null) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("Profile type is required");
        }
    }
}