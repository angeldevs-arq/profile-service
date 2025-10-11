package com.angeldevs.profileservice.profiles.application.internal.queryservices;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.ServiceCatalog;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogByIdQuery;
import com.angeldevs.profileservice.profiles.domain.model.queries.GetServiceCatalogsByProfileIdQuery;
import com.angeldevs.profileservice.profiles.domain.services.ServiceCatalogQueryService;
import com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories.ServiceCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ServiceCatalog query service.
 */
@Service
public class ServiceCatalogQueryServiceImpl implements ServiceCatalogQueryService {

    private final ServiceCatalogRepository serviceCatalogRepository;

    public ServiceCatalogQueryServiceImpl(ServiceCatalogRepository serviceCatalogRepository) {
        this.serviceCatalogRepository = serviceCatalogRepository;
    }

    @Override
    public List<ServiceCatalog> handle(GetServiceCatalogsByProfileIdQuery query) {
        return serviceCatalogRepository.findByProfile_Id(query.profileId());
    }

    @Override
    public Optional<ServiceCatalog> handle(GetServiceCatalogByIdQuery query) {
        return serviceCatalogRepository.findById(query.serviceCatalogId());
    }
}