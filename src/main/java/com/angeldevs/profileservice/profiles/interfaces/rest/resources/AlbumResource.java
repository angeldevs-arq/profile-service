package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

import java.util.List;

/**
 * Album resource representation.
 *
 * @param id album id
 * @param profileId owner profile id
 * @param title album title
 * @param description album description
 * @param photos list of photo URLs
 */
public record AlbumResource(Long id, Long profileId, String title, String description, List<PhotoResource> photos) {
}