package com.angeldevs.profileservice.profiles.interfaces.rest;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogsByProfileIdQuery;
import com.angeldevs.profileservice.profiles.domain.services.ServiceCatalogCommandService;
import com.angeldevs.profileservice.profiles.domain.services.ServiceCatalogQueryService;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateServiceCatalogResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.ServiceCatalogResource;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.CreateServiceCatalogCommandFromResourceAssembler;
import com.angeldevs.profileservice.profiles.interfaces.rest.transform.ServiceCatalogResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for service catalog management.
 */
@RestController
@RequestMapping(value = "/api/v1/{profileId}/service-catalogs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Service Catalogs", description = "Service Catalog Management Endpoints")
public class ServiceCatalogsController {

    private final ServiceCatalogCommandService commandService;
    private final ServiceCatalogQueryService queryService;

    public ServiceCatalogsController(ServiceCatalogCommandService commandService, ServiceCatalogQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Create Service Catalog")
    @PostMapping
    public ResponseEntity<?> createCatalog(@PathVariable Long profileId, @RequestBody CreateServiceCatalogResource resource) {
        CreateServiceCatalogCommand command = CreateServiceCatalogCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        var catalogId = commandService.handle(command);
        if (catalogId.isEmpty()) return ResponseEntity.badRequest().build();
        var catalog = queryService.handle(new GetServiceCatalogByIdQuery(catalogId.get()));
        if (catalog.isEmpty()) return ResponseEntity.badRequest().build();
        ServiceCatalogResource catalogResource = ServiceCatalogResourceFromEntityAssembler.toResourceFromEntity(catalog.get());
        return new ResponseEntity<>(catalogResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Catalogs by Profile")
    @GetMapping
    public ResponseEntity<List<ServiceCatalogResource>> getCatalogsByProfile(@PathVariable Long profileId) {
        var catalogs = queryService.handle(new GetServiceCatalogsByProfileIdQuery(profileId));
        var resources = catalogs.stream().map(ServiceCatalogResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get Service Catalog")
    @GetMapping("/{catalogId}")
    public ResponseEntity<?> getCatalog(@PathVariable Long profileId, @PathVariable Long catalogId) {
        var catalog = queryService.handle(new GetServiceCatalogByIdQuery(catalogId));
        return catalog.map(value ->
                        value.getProfile().getId().equals(profileId)
                                ? ResponseEntity.ok(ServiceCatalogResourceFromEntityAssembler.toResourceFromEntity(value))
                                : ResponseEntity.status(HttpStatus.FORBIDDEN).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update Service Catalog")
    @PutMapping("/{catalogId}")
    public ResponseEntity<?> updateCatalog(@PathVariable Long profileId, @PathVariable Long catalogId, @RequestBody CreateServiceCatalogResource resource) {
        var command = new UpdateServiceCatalogCommand(
                catalogId,
                resource.title(),
                resource.description(),
                resource.category(),
                resource.priceFrom(),
                resource.priceTo()
        );
        commandService.handle(command);
        var catalog = queryService.handle(new GetServiceCatalogByIdQuery(catalogId));
        return catalog.map(value ->
                        value.getProfile().getId().equals(profileId)
                                ? ResponseEntity.ok(ServiceCatalogResourceFromEntityAssembler.toResourceFromEntity(value))
                                : ResponseEntity.status(HttpStatus.FORBIDDEN).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Service Catalog")
    @DeleteMapping("/{catalogId}")
    public ResponseEntity<?> deleteCatalog(@PathVariable Long profileId, @PathVariable Long catalogId) {
        var catalog = queryService.handle(new GetServiceCatalogByIdQuery(catalogId));
        if (catalog.isEmpty()) return ResponseEntity.notFound().build();
        if (!catalog.get().getProfile().getId().equals(profileId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        commandService.deleteById(catalogId);
        return ResponseEntity.ok().build();
    }
}