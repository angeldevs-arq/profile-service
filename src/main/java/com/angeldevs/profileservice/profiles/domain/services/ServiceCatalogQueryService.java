package com.angeldevs.profileservice.profiles.domain.services;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.ServiceCatalog;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogsByProfileIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * ServiceCatalog query service contract.
 */
public interface ServiceCatalogQueryService {

    List<ServiceCatalog> handle(GetServiceCatalogsByProfileIdQuery query);

    Optional<ServiceCatalog> handle(GetServiceCatalogByIdQuery query);
}