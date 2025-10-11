package com.angeldevs.profileservice.profiles.application.internal.commandservices;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Profile;
import com.angeldevs.profileservice.profiles.domain.model.aggregates.ServiceCatalog;
import com.angeldevs.profileservice.profiles.domain.model.commands.CreateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateServiceCatalogCommand;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;
import com.angeldevs.profileservice.profiles.domain.services.ServiceCatalogCommandService;
import com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories.ServiceCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of ServiceCatalog command service.
 */
@Service
public class ServiceCatalogCommandServiceImpl implements ServiceCatalogCommandService {

    private final ServiceCatalogRepository serviceCatalogRepository;
    private final ProfileRepository profileRepository;

    public ServiceCatalogCommandServiceImpl(ServiceCatalogRepository serviceCatalogRepository, ProfileRepository profileRepository) {
        this.serviceCatalogRepository = serviceCatalogRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Long> handle(CreateServiceCatalogCommand command) {
        var profileOpt = profileRepository.findById(command.profileId());
        if (profileOpt.isEmpty()) return Optional.empty();
        Profile profile = profileOpt.get();
        if (profile.getType() != ProfileType.ORGANIZER) return Optional.empty();
        var catalog = new ServiceCatalog(profile, command.title(), command.description(), command.category(), command.priceFrom(), command.priceTo());
        serviceCatalogRepository.save(catalog);
        return Optional.of(catalog.getId());
    }

    @Override
    public void handle(UpdateServiceCatalogCommand command) {
        var catalogOpt = serviceCatalogRepository.findById(command.serviceCatalogId());
        if (catalogOpt.isEmpty()) return;
        var catalog = catalogOpt.get();
        catalog.update(command.title(), command.description(), command.category(), command.priceFrom(), command.priceTo());
        serviceCatalogRepository.save(catalog);
    }

    @Override
    public void deleteById(Long serviceCatalogId) {
        serviceCatalogRepository.deleteById(serviceCatalogId);
    }
}