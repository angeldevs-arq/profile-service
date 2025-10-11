package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

/**
 * Resource to create or update a service catalog.
 *
 * @param profileId owner profile id
 * @param title catalog title
 * @param description catalog description
 * @param category catalog category
 * @param priceFrom price range from
 * @param priceTo price range to
 */
public record CreateServiceCatalogResource(
        Long profileId,
        String title,
        String description,
        String category,
        Double priceFrom,
        Double priceTo) {
}