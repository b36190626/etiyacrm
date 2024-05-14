package com.etiya.catalogservice.entities;

import com.etiya.catalogservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_offer_id")
    private ProductOffer productOffer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
