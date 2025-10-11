package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateAlbumResource;

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
    public static CreateAlbumCommand toCommandFromResource(Long profileId, CreateAlbumResource resource) {
        return new CreateAlbumCommand(
                profileId,
                resource.title(),
                resource.description(),
                resource.photos()
        );
    }
}