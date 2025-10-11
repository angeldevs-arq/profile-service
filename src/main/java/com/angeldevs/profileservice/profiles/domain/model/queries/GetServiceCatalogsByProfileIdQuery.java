package com.angeldevs.profileservice.profiles.domain.model.queries;

/**
 * Query to retrieve service catalog entries by profile id.
 *
 * @param profileId profile identifier
 */
public record GetServiceCatalogsByProfileIdQuery(Long profileId) {
}