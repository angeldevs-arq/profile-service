package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetAlbumByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetAlbumsByProfileIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Album query service contract.
 */
public interface AlbumQueryService {


    /**
     * Get albums for a profile.
     *
     * @param query albums by profile id query
     * @return list of albums
     */
    List<Album> handle(GetAlbumsByProfileIdQuery query);


    /**
     * Get album by id.
     *
     * @param query album id query
     * @return optional album
     */
    Optional<Album> handle(GetAlbumByIdQuery query);
}