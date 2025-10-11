package com.angeldevs.profileservice.profiles.domain.model.queries;

/**
 * Query to retrieve a service catalog by id.
 *
 * @param serviceCatalogId catalog identifier
 */
public record GetServiceCatalogByIdQuery(Long serviceCatalogId) {
}