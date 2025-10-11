package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.AlbumResource;

/**
 * Assembler to convert Album entity to AlbumResource.
 */
public class AlbumResourceFromEntityAssembler {
    /**
     * Convert entity to resource.
     *
     * @param entity album entity
     * @return album resource
     */
    public static AlbumResource toResourceFromEntity(Album entity) {
        return new AlbumResource(
                entity.getId(),
                entity.getProfile().getId(),
                entity.getTitle(),
                entity.getDescription(),
                java.util.List.copyOf(entity.getPhotos())
        );
    }
}