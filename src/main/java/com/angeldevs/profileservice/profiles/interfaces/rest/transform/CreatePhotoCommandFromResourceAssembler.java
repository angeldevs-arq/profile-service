package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreatePhotoInAlbumCommand;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreatePhotoResource;

public class CreatePhotoCommandFromResourceAssembler {
    public static CreatePhotoInAlbumCommand toCommandFromResource(Long albumId,CreatePhotoResource resource) {
        return new  CreatePhotoInAlbumCommand(albumId,resource.photoUrl(),resource.photoPublicId());
    }
}
