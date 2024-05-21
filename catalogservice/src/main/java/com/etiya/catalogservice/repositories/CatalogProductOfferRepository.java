package com.etiya.catalogservice.repositories;

import com.etiya.catalogservice.entities.CatalogProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, String> {
    List<CatalogProductOffer> findAllByProductOfferId (String productOfferId);
    CatalogProductOffer findByProductOfferId (String productOfferId);
    List<CatalogProductOffer> findAllByCatalogId (String catalogId);
    CatalogProductOffer findByCatalogId(String catalogId);
}
