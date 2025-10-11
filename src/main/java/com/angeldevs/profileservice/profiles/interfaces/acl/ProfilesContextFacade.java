package com.angeldevs.profileservice.profiles.interfaces.acl;

import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;

/**
 * ProfilesContextFacade
 *
 * @summary
 * Facade interface for Anti-Corruption Layer.
 * Provides simplified access to profiles context for other bounded contexts.
 *
 * @since 1.0
 */
public interface ProfilesContextFacade {

    /**
     * Create profile with complete information
     *
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param street Street address
     * @param number Street number
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     * @param type Profile type
     * @return Profile ID if created successfully, 0 otherwise
     */
    Long createProfile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, ProfileType type);

    /**
     * Create profile with minimal information
     *
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @return Profile ID if created successfully, 0 otherwise
     */
    Long createProfile(String firstName, String lastName, String email, ProfileType type);

    /**
     * Get profile ID by email address
     *
     * @param email Email address
     * @return Profile ID if found, 0 otherwise
     */
    Long fetchProfileIdByEmail(String email);

    /**
     * Get full name by profile ID
     *
     * @param profileId Profile ID
     * @return Full name if profile exists, empty string otherwise
     */
    String fetchFullNameByProfileId(Long profileId);

    /**
     * Get email address by profile ID
     *
     * @param profileId Profile ID
     * @return Email address if profile exists, empty string otherwise
     */
    String fetchEmailByProfileId(Long profileId);


    /**
     * Get profile ID by full name
     *
     * @param firstName first name of the person
     * @param lastName last name of the person
     * @return Profile ID if found, 0 otherwise
     */
    Long fetchProfileIdByFullName(String firstName, String lastName);

    /**
     * Validate if a profile existent correspond to an organizer.
     *
     * @param profileId Profile identifier
     * @return {@code true} if the profile type is ORGANIZER, {@code false} otherwise
     */
    boolean isOrganizerProfile(Long profileId);
}