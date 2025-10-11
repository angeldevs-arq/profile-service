package com.angeldevs.profileservice.profiles.interfaces.rest.transform;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.interfaces.rest.resources.CreateServiceCatalogResource;

/**
 * Assembler to convert CreateServiceCatalogResource to CreateServiceCatalogCommand.
 */
public class CreateServiceCatalogCommandFromResourceAssembler {

    public static CreateServiceCatalogCommand toCommandFromResource(Long profileId, CreateServiceCatalogResource resource) {
        return new CreateServiceCatalogCommand(
                profileId,
                resource.title(),
                resource.description(),
                resource.category(),
                resource.priceFrom(),
                resource.priceTo()
        );
    }
}