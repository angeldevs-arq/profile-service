package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateProfileCommand;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long id,UpdateProfileResource resource){
        return new UpdateProfileCommand(
                id, resource.firstName(), resource.lastName(),
                resource.email(), resource.street(), resource.number(),
                resource.city(), resource.postalCode(), resource.country(),
                resource.profileImageUrl(),  resource.profileImagePublicId(),
                ProfileType.valueOf(resource.type())
        );
    }
}
