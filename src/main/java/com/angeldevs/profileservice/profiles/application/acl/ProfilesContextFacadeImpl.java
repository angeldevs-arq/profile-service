package com.angeldevs.profileservice.profiles.application.acl;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateProfileCommand;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByFullNameQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;
import com.angeldevs.profileservice.profiles.domain.services.ProfileCommandService;
import com.angeldevs.profileservice.profiles.domain.services.ProfileQueryService;
import com.angeldevs.profileservice.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

/**
 * ProfilesContextFacadeImpl
 *
 * @summary
 * Implementation of ProfilesContextFacade for Anti-Corruption Layer.
 * Provides simplified interface for other bounded contexts to interact with profiles.
 *
 * @since 1.0
 */
@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacadeImpl(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create profile from external context
     *
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @param street Street address
     * @param number Street number
     * @param city City
     * @param postalCode Postal code
     * @param country Country
     * @return Profile ID if created successfully, 0 otherwise
     */
    @Override
    public Long createProfile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, ProfileType type) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street, number, city, postalCode, country, type);
        var profileId = profileCommandService.handle(createProfileCommand);
        return profileId.orElse(0L);
    }

    /**
     * Create profile with minimal information
     *
     * @param firstName First name
     * @param lastName Last name
     * @param email Email address
     * @return Profile ID if created successfully, 0 otherwise
     */
    @Override
    public Long createProfile(String firstName, String lastName, String email, ProfileType type) {
        return createProfile(firstName, lastName, email, null, null, null, null, null,null);
    }

    /**
     * Get profile ID by email address
     *
     * @param email Email address
     * @return Profile ID if found, 0 otherwise
     */
    @Override
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(email);
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        return profile.map(p -> p.getId()).orElse(0L);
    }

    /**
     * Get full name by profile ID
     *
     * @param profileId Profile ID
     * @return Full name if profile exists, empty string otherwise
     */
    @Override
    public String fetchFullNameByProfileId(Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        return profile.map(p -> p.getFullName()).orElse("");
    }

    /**
     * Get email address by profile ID
     *
     * @param profileId Profile ID
     * @return Email address if profile exists, empty string otherwise
     */
    @Override
    public String fetchEmailByProfileId(Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        return profile.map(p -> p.getEmailAddress()).orElse("");
    }

    public Long fetchProfileIdByFullName(String firstName, String lastName) {
        var getProfileByFullNameQuery = new GetProfileByFullNameQuery(firstName, lastName);
        var profile = profileQueryService.handle(getProfileByFullNameQuery);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }

    /**
     * Check if a profile is of type ORGANIZER.
     *
     * @param profileId profile identifier
     * @return {@code true} when the profile exists and is an organizer
     */
    @Override
    public boolean isOrganizerProfile(Long profileId)
    {
        var getprofileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getprofileByIdQuery);
        return profile.map(p->p.getType()== ProfileType.ORGANIZER).orElse(false);
    }
}