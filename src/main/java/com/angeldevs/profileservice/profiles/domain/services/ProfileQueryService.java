package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Profile;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetAllProfilesQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByFullNameQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * ProfileQueryService
 *
 * @summary
 * Service interface for handling Profile queries.
 * Defines all query operations for Profile aggregate.
 *
 * @since 1.0
 */
public interface ProfileQueryService {

    /**
     * Handle GetAllProfilesQuery
     *
     * @param query GetAllProfilesQuery to handle
     * @return List of all profiles
     */
    List<Profile> handle(GetAllProfilesQuery query);

    /**
     * Handle GetProfileByIdQuery
     *
     * @param query GetProfileByIdQuery to handle
     * @return Optional containing the profile if found
     */
    Optional<Profile> handle(GetProfileByIdQuery query);

    /**
     * Handle GetProfileByEmailQuery
     *
     * @param query GetProfileByEmailQuery to handle
     * @return Optional containing the profile if found
     */
    Optional<Profile> handle(GetProfileByEmailQuery query);

    /**
     * Handle GetProfileIdByFullNameQuery
     *
     * @param query GetProfileIdByFullNameQuery to handle
     * @return Optional containing the profile if found
     */
    Optional<Profile> handle(GetProfileByFullNameQuery query);
}