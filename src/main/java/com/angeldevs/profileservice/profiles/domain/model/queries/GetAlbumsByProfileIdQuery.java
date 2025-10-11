package com.angeldevs.profileservice.profiles.domain.model.queries;

/**
 * Query to retrieve albums by profile id.
 *
 * @param profileId profile identifier
 */
public record GetAlbumsByProfileIdQuery(Long profileId) {
}