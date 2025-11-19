package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.interfaces.rest.resources.MediaResource;

public class MediaResourceFromEntityAssembler {
    public static MediaResource toResourceFromEntity(String secure_url, String public_id) {
        return new MediaResource(secure_url, public_id);
    }
}
