package com.angeldevs.profileservice.profiles.domain.model.aggregates;

import com.angeldevs.profileservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * ServiceCatalog aggregate root within Profile context.
 */
@Getter
@Entity
@Table(name = "service_catalogs")
public class ServiceCatalog extends AuditableAbstractAggregateRoot<ServiceCatalog> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(name = "price_from", nullable = false)
    private Double priceFrom;

    @Column(name = "price_to", nullable = false)
    private Double priceTo;

    protected ServiceCatalog() {
        // Required by JPA
    }

    public ServiceCatalog(Profile profile, String title, String description, String category, Double priceFrom, Double priceTo) {
        this();
        this.profile = profile;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public ServiceCatalog update(String title, String description, String category, Double priceFrom, Double priceTo) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        return this;
    }
}