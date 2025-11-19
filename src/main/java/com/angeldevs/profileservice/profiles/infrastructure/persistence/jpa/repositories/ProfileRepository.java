package com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Profile;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProfileRepository
 *
 * @summary
 * Repository interface for Profile aggregate.
 * Provides data access methods for Profile entities.
 *
 * @since 1.0
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Find profile by email address
     *
     * @param emailAddress EmailAddress value object
     * @return Optional containing the profile if found
     */
    Optional<Profile> findByEmail(EmailAddress emailAddress);


    /**
     * Find profile by id
     * @param id identifier of profile aggregate
     * @return Optional containing the profile if found
     */
    Optional<Profile> findById(Long id);

    /**
     * Find profile by email address string
     *
     * @param emailAddress Email address as string
     * @return Optional containing the profile if found
     */
    Optional<Profile> findByEmail_Address(String emailAddress);

    /**
     * Check if profile exists by email address
     *
     * @param emailAddress EmailAddress value object
     * @return true if profile exists, false otherwise
     */
    boolean existsByEmail(EmailAddress emailAddress);

    /**
     * Check if profile exists by email address string
     *
     * @param emailAddress Email address as string
     * @return true if profile exists, false otherwise
     */
    boolean existsByEmail_Address(String emailAddress);

    Optional<Profile> findByName_FirstNameAndName_LastName(String firstName, String lastName);
}