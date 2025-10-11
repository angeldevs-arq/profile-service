package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateAlbumCommand;

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
}