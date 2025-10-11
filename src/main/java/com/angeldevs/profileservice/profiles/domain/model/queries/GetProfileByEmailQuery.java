package com.angeldevs.profileservice.profiles.domain.model.queries;

/**
 * GetProfileByEmailQuery
 *
 * @summary
 * Query to get a profile by email address.
 *
 * @param emailAddress Email address to search for
 * @since 1.0
 */
public record GetProfileByEmailQuery(String emailAddress) {
}