package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

/**
 * Resource to create an album.
 *
 * @param profileId owner profile id
 * @param title album title
 * @param description album description
 * @param photos list of photo URLs
 */
public record CreateAlbumResource(Long profileId, String title, String description, java.util.List<String> photos) {
}