package com.angeldevs.profileservice.profiles.interfaces.rest;

import com.angeldevs.profileservice.profiles.domain.model.queries.GetAllProfilesQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetProfileByIdQuery;
import com.angeldevs.profileservice.profiles.domain.services.ProfileCommandService;
import com.angeldevs.profileservice.profiles.domain.services.ProfileQueryService;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateProfileResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.ProfileResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProfilesController
 *
 * @summary
 * REST controller for Profile aggregate.
 * Provides endpoints for profile management operations.
 *
 * @since 1.0
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create a new profile
     *
     * @param createProfileResource CreateProfileResource with profile data
     * @return ProfileResource if successful, error message otherwise
     */
    @Operation(summary = "Create New Profile",
            description = "Creates a new profile with the provided data. Returns the created profile resource.")
    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody CreateProfileResource createProfileResource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(createProfileResource);
        var profileId = profileCommandService.handle(createProfileCommand);
        if (profileId.isEmpty()) return ResponseEntity.badRequest().build();
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId.get());
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    /**
     * Get profile by ID
     *
     * @param profileId Profile ID
     * @return ProfileResource if found, 404 otherwise
     */
    @GetMapping("/{profileId}")
    @Operation(summary = "Get Profile by ID", description = "Retrieves a profile by its unique ID. Returns the profile resource if found.")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Get profile by email
     *
     * @param email Email address
     * @return ProfileResource if found, 404 otherwise
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Get Profile by Email",
            description = "Retrieves a profile by its email address. Returns the profile resource if found.")
    public ResponseEntity<ProfileResource> getProfileByEmail(@PathVariable String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(email);
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a profile", description = "Update a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileResource resource) {
        var updatedProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedProfile = profileCommandService.handle(updatedProfileCommand);
        if (updatedProfile.isEmpty()) return ResponseEntity.notFound().build();
        var updatedProfileEntity = updatedProfile.get();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfileEntity);
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Get all profiles
     *
     * @return List of ProfileResource
     */
    @Operation(summary = "Get All Profiles",
            description = "Retrieves a list of all profiles. Returns a list of profile resources.")
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profileResources);
    }
}