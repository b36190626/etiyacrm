package com.etiya.basketservice.clients;

import com.etiya.basketservice.services.dtos.responses.GetCatalogProductOfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "http://localhost:8001/catalog-service/api/v1/catalog-product-offers") //gateway gelince catalog-service eklenecek
public interface CatalogServiceClient {

    @GetMapping("/{id}")
    GetCatalogProductOfferResponse getById(@PathVariable String id);
}
