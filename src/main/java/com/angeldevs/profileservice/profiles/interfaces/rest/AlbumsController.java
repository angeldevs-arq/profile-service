package com.angeldevs.profileservice.profiles.interfaces.rest;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetAlbumByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetAlbumsByProfileIdQuery;
import com.angeldevs.profileservice.profiles.domain.services.AlbumCommandService;
import com.angeldevs.profileservice.profiles.domain.services.AlbumQueryService;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.AlbumResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateAlbumResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.AlbumResourceFromEntityAssembler;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.CreateAlbumCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for album management.
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/{profileId}/albums", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Albums", description = "Album Management Endpoints")
public class AlbumsController {

    private final AlbumCommandService albumCommandService;
    private final AlbumQueryService albumQueryService;

    public AlbumsController(AlbumCommandService albumCommandService, AlbumQueryService albumQueryService) {
        this.albumCommandService = albumCommandService;
        this.albumQueryService = albumQueryService;
    }

    /**
     * Create an album for an organizer profile.
     */
    @Operation(summary = "Create Album")
    @PostMapping
    public ResponseEntity<?> createAlbum(@PathVariable Long profileId, @RequestBody CreateAlbumResource resource) {
        CreateAlbumCommand command = CreateAlbumCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        var albumId = albumCommandService.handle(command);
        if (albumId.isEmpty()) return ResponseEntity.badRequest().build();
        var album = albumQueryService.handle(new GetAlbumByIdQuery(albumId.get()));
        if (album.isEmpty()) return ResponseEntity.badRequest().build();
        AlbumResource albumResource = AlbumResourceFromEntityAssembler.toResourceFromEntity(album.get());
        return new ResponseEntity<>(albumResource, HttpStatus.CREATED);
    }

    /**
     * Get albums by profile id.
     */
    @Operation(summary = "Get Albums by Profile")
    @GetMapping
    public ResponseEntity<List<AlbumResource>> getAlbumsByProfile(@PathVariable Long profileId) {
        var albums = albumQueryService.handle(new GetAlbumsByProfileIdQuery(profileId));
        var resources = albums.stream().map(AlbumResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get a single album by id.
     */
    @Operation(summary = "Get Album")
    @GetMapping("/{albumId}")
    public ResponseEntity<?> getAlbum(@PathVariable Long profileId, @PathVariable Long albumId) {
        var album = albumQueryService.handle(new GetAlbumByIdQuery(albumId));
        return album.map(value ->
                        value.getProfile().getId().equals(profileId)
                                ? ResponseEntity.ok(AlbumResourceFromEntityAssembler.toResourceFromEntity(value))
                                : ResponseEntity.status(HttpStatus.FORBIDDEN).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update an album.
     */
    @Operation(summary = "Update Album")
    @PutMapping("/{albumId}")
    public ResponseEntity<?> updateAlbum(@PathVariable Long profileId, @PathVariable Long albumId,
                                         @RequestBody CreateAlbumResource resource) {
        var command = new UpdateAlbumCommand(
                albumId,
                resource.title(),
                resource.description(),
                resource.photos()
        );
        albumCommandService.handle(command);
        var album = albumQueryService.handle(new GetAlbumByIdQuery(albumId));
        return album.map(value ->
                        value.getProfile().getId().equals(profileId)
                                ? ResponseEntity.ok(AlbumResourceFromEntityAssembler.toResourceFromEntity(value))
                                : ResponseEntity.status(HttpStatus.FORBIDDEN).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete an album.
     */
    @Operation(summary = "Delete Album")
    @DeleteMapping("/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long profileId, @PathVariable Long albumId) {
        var album = albumQueryService.handle(new GetAlbumByIdQuery(albumId));
        if (album.isEmpty()) return ResponseEntity.notFound().build();
        if (!album.get().getProfile().getId().equals(profileId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        albumCommandService.deleteById(albumId);
        return ResponseEntity.ok().build();
    }
}