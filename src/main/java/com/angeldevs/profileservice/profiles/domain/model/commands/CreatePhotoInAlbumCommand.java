package com.angeldevs.profileservice.profiles.domain.model.commands;

public record CreatePhotoInAlbumCommand(Long albumId, String photoUrl, String publicId) {
}
