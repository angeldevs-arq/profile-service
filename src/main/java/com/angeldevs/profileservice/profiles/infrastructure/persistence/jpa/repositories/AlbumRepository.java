package com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for album aggregate.
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * Find albums by profile id.
     *
     * @param profileId profile identifier
     * @return list of albums
     */
    List<Album> findByProfile_Id(Long profileId);
}