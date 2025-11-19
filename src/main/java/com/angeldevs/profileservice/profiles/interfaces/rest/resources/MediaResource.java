package com.angeldevs.profileservice.profiles.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record MediaResource(String imageUrl, String imagePublicId) {
}
