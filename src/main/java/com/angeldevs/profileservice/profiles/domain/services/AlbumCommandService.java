package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.CreatePhotoInAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.DeletePhotoFromAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.Photo;

import java.util.Optional;

/**
 * Album command service contract.
 */
public interface AlbumCommandService {

    /**
     * Handle album creation.
     *
     * @param command create album command
     * @return optional album id
     */
    Optional<Long> handle(CreateAlbumCommand command);

    /**
     * Handle album update.
     *
     * @param command update album command
     */
    void handle(UpdateAlbumCommand command);

    /**
     * Delete album by id.
     *
     * @param albumId album identifier
     */
    void deleteById(Long albumId);


    //Create and Delete Photo into an album
    void handle(CreatePhotoInAlbumCommand command);

    void handle(DeletePhotoFromAlbumCommand command);
}