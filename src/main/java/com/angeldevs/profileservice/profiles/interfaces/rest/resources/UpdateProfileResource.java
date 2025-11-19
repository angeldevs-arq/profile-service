package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

public record UpdateProfileResource(
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
        String type
){}
