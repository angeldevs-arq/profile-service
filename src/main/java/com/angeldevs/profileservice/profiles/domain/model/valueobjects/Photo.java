package com.angeldevs.profileservice.profiles.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Photo {

    @Column(name = "photo_url", nullable = false, length = 1024)
    private String url;

    @Column(name = "photo_public_id", nullable = false, length = 255)
    private String publicId;

    /*@Column(name="album_id", nullable = false)
    private Long albumId;*/

    protected Photo() {}

    public Photo(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }
}
