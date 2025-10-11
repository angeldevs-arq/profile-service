package com.angeldevs.profileservice.profiles.domain.model.commands;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;

import java.util.List;

/**
 * Command to create a new album for a profile.
 *
 * @param profileId owner profile ID
 * @param title album title
 * @param description album description
 * @param photos list of photo URLs
 */
public record CreateAlbumCommand(Long profileId, String title, String description, List<String> photos) {
    /**
     * Validates required fields.
     *
     * @param profileId owner profile ID
     * @param title album title
     * @param description album description
     */
    public CreateAlbumCommand {
        if (profileId == null || profileId <= 0) {
            throw new IllegalArgumentException("Profile id is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (photos != null && photos.size() > Album.MAX_PHOTOS) {
            throw new IllegalArgumentException("Album can contain at most " + Album.MAX_PHOTOS + " photos");
        }
    }
}