package com.angeldevs.profileservice.profiles.domain.model.aggregates;

import com.angeldevs.profileservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Album aggregate root within Profile context.
 */
@Getter
@Entity
@Table(name = "albums")
public class Album extends AuditableAbstractAggregateRoot<Album> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    /** Maximum number of photos an album can contain. */
    public static final int MAX_PHOTOS = 10;

    @ElementCollection
    @CollectionTable(name = "album_photos", joinColumns = @JoinColumn(name = "album_id"))
    @Column(name = "photo_url", nullable = false, length = 1024)
    private List<String> photos = new ArrayList<>();

    protected Album() {
        // Required by JPA
    }

    /**
     * Album constructor.
     *
     * @param profile owning profile
     * @param title album title
     * @param description album description
     * @param photos list of photo URLs
     */
    public Album(Profile profile, String title, String description, List<String> photos) {
        this();
        this.profile = profile;
        this.title = title;
        this.description = description;
        if (photos != null) {
            if (photos.size() > MAX_PHOTOS) {
                throw new IllegalArgumentException("Album can contain at most " + MAX_PHOTOS + " photos");
            }
            this.photos.addAll(photos);
        }
    }

    /**
     * Update album information.
     *
     * @param title new title
     * @param description new description
     * @param photos list of photo URLs
     * @return updated album
     */
    public Album update(String title, String description, List<String> photos) {
        this.title = title;
        this.description = description;
        if (photos != null) {
            if (photos.size() > MAX_PHOTOS) {
                throw new IllegalArgumentException("Album can contain at most " + MAX_PHOTOS + " photos");
            }
            this.photos.clear();
            this.photos.addAll(photos);
        }
        return this;
    }
}