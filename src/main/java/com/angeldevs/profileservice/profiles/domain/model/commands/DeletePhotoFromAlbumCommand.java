package com.angeldevs.profileservice.profiles.domain.model.commands;

public record DeletePhotoFromAlbumCommand(Long albumId, String publicId) {
}
