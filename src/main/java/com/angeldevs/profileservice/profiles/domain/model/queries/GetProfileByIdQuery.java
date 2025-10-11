package com.angeldevs.profileservice.profiles.domain.model.queries;

/**
 * GetProfileByIdQuery
 *
 * @summary
 * Query to get a profile by ID.
 *
 * @param profileId Profile ID to search for
 * @since 1.0
 */
public record GetProfileByIdQuery(Long profileId) {
}