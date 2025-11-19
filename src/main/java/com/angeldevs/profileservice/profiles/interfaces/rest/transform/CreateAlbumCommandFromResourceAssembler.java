package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.Photo;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateAlbumResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.PhotoResource;

import java.util.List;

/**
 * Assembler to convert CreateAlbumResource to CreateAlbumCommand.
 */
public class CreateAlbumCommandFromResourceAssembler {
    /**
     * Convert resource to command.
     *
     * @param resource create album resource
     * @return create album command
     */
    public static CreateAlbumCommand toCommandFromResource(CreateAlbumResource resource) {

        List<Photo> photos = resource.photos() != null
                ? resource.photos().stream()
                .map(p -> new Photo(p.photoUrl(), p.photoPublicId()))
                .toList()
                : List.of();

        return new CreateAlbumCommand(
                resource.profileId(),
                resource.title(),
                resource.description(),photos
        );
    }
}