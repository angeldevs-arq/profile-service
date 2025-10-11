package com.angeldevs.profileservice.profiles.domain.model.commands;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;

import java.util.List;

/**
 * Command to update an existing album.
 *
 * @param albumId album identifier
 * @param title new title
 * @param description new description
 * @param photos list of photo URLs
 */
public record UpdateAlbumCommand(Long albumId, String title, String description, List<String> photos) {
    /**
     * Validates input fields.
     *
     * @param albumId album identifier
     * @param title title
     * @param description description
     */
    public UpdateAlbumCommand {
        if (albumId == null || albumId <= 0) {
            throw new IllegalArgumentException("Album id is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (photos != null && photos.size() > Album.MAX_PHOTOS) {
            throw new IllegalArgumentException("Album can contain at most " + Album.MAX_PHOTOS + " photos");
        }
    }
}