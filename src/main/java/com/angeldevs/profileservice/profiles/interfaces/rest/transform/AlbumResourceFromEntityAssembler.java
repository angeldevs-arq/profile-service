package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.AlbumResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.PhotoResource;

import java.util.List;

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
        List<PhotoResource> photos = entity.getPhotos().stream()
                .map(p -> new PhotoResource(p.getUrl(), p.getPublicId()))
                .toList();

        return new AlbumResource(
                entity.getId(),
                entity.getProfile().getId(),
                entity.getTitle(),
                entity.getDescription(),photos
        );
    }
}