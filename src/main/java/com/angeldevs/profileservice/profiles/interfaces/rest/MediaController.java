package com.angeldevs.profileservice.profiles.interfaces.rest;

import com.angeldevs.profileservice.profiles.interfaces.rest.resources.MediaResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.MediaResourceFromEntityAssembler;
import com.angeldevs.profileservice.shared.infrastructure.cloudinary.services.CloudinaryService;
import com.angeldevs.profileservice.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "api/v1/media", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Medias", description = "Available media endpoints")
public class MediaController {
    private final CloudinaryService cloudinaryService;

    public MediaController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a new image", description = "Upload a image to cloudinary")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Image Uploaded Successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid image")
    })
    public ResponseEntity<MediaResource> uploadImage(@RequestPart("image")MultipartFile image, @RequestParam("folder") String folder){
        var mediaResult = cloudinaryService.uploadImage(image, folder);
        if(mediaResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var mediaResource = MediaResourceFromEntityAssembler.toResourceFromEntity(mediaResult.get().getLeft(), mediaResult.get().getRight());
        return ResponseEntity.ok(mediaResource);
    }

    @PutMapping(value = "/update",  consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update image", description = "Update image of cloudinary by public Id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Image updated successfully"),
            @ApiResponse(responseCode = "400", description = "Cannot update image")
    })
    public ResponseEntity<MediaResource> updateImage(@RequestPart("image") MultipartFile image, @RequestParam("publicId") String publicId,@RequestParam("folder") String folder){

        Optional<ImmutablePair<String, String>> mediaResult;

        if(publicId.equals("default-profile_kxt5l2") && folder.equals("profiles") ){
            mediaResult = cloudinaryService.uploadImage(image, folder);
        }else{
            mediaResult = cloudinaryService.updateImage(image, publicId, folder);
        }

        if(mediaResult.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var mediaResource = MediaResourceFromEntityAssembler.toResourceFromEntity(mediaResult.get().getLeft(), mediaResult.get().getRight());
        return ResponseEntity.ok(mediaResource);
    }

    @DeleteMapping(value = "delete/{publicId}")
    @Operation(summary = "Delete image", description = "Delete image from cloudinary using publicId")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Image deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Cannot delete image")
    })
    public ResponseEntity<?> deleteImage(@PathVariable("publicId") String publicId){
        var deleted = cloudinaryService.deleteImage(publicId);
        return deleted ? ResponseEntity.ok(Map.of("message","Image deleted successfully")) : ResponseEntity.badRequest().build();
    }
}
