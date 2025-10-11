package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

/**
 * CreateProfileResource
 *
 * @summary
 * Resource for creating a new profile.
 * Contains all necessary data for profile creation through REST API.
 *
 * @param firstName First name
 * @param lastName Last name
 * @param email Email address
 * @param street Street address
 * @param number Street number
 * @param city City
 * @param postalCode Postal code
 * @param country Country
 * @param type profile type
 * @since 1.0
 */
public record CreateProfileResource(
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country,
        String type
) {
}