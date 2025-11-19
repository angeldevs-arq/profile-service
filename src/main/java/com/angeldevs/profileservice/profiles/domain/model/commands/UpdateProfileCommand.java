package com.angeldevs.profileservice.profiles.domain.model.commands;

import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;

public record UpdateProfileCommand(
        Long id,
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country,
        String profileImageUrl,
        String profileImagePublicId,
        ProfileType type) {
}
