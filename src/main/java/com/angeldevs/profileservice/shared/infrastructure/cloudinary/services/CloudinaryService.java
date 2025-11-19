package com.angeldevs.profileservice.shared.infrastructure.cloudinary.services;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public Optional<ImmutablePair<String,String>> uploadImage(MultipartFile file, String folder){
        try {
            Map<?,?> result = cloudinary.uploader().upload(file.getBytes(), Map.of("folder",folder));
            String url = result.get("secure_url").toString();
            String publicId =  result.get("public_id").toString();
            return Optional.of(ImmutablePair.of(url,publicId));

        }catch (Exception e){
            throw new RuntimeException("Error uploading image: " + e.getMessage());
        }
    }

    public Optional<ImmutablePair<String,String>> updateImage(MultipartFile file, String publicId, String folder){
        try {
            // elimina primero
            cloudinary.uploader().destroy(publicId, Map.of());

            // sube nueva
            Map<?,?> result = cloudinary.uploader().upload(file.getBytes(), Map.of("folder",folder));

            return Optional.of(ImmutablePair.of(result.get("secure_url").toString(), result.get("public_id").toString()));
        }catch (Exception e){
            throw new RuntimeException("Error updating image: " + e.getMessage());
        }
    }

    public boolean deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, Map.of());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar imagen: " + e.getMessage());
        }
    }
}
