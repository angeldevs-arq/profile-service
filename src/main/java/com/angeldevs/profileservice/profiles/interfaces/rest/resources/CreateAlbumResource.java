package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

import java.util.List;

/**
 * Resource to create an album.
 *
 * @param profileId owner profile id
 * @param title album title
 * @param description album description
 * @param photos list of photo URLs
 */
/*public record CreateAlbumResource(Long profileId, String title, String description, List<PhotoResource> photos) {
}*/
public record CreateAlbumResource(Long profileId, String title, String description, List<PhotoResource> photos) {
}