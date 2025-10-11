package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.commands.CreateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateServiceCatalogCommand;

import java.util.Optional;

/**
 * ServiceCatalog command service contract.
 */
public interface ServiceCatalogCommandService {

    Optional<Long> handle(CreateServiceCatalogCommand command);

    void handle(UpdateServiceCatalogCommand command);

    void deleteById(Long serviceCatalogId);
}