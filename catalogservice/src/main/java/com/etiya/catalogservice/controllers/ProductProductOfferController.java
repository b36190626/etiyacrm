package com.etiya.catalogservice.controllers;

import com.etiya.catalogservice.services.abstracts.ProductProductOfferService;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.CreateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.UpdateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product_product_offers")
public class ProductProductOfferController {
    private ProductProductOfferService productProductOfferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add")
    public CreatedProductProductOfferResponse add(@Valid @RequestBody CreateProductProductOfferRequest createProductProductOfferRequest) {
        return productProductOfferService.add(createProductProductOfferRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update")
    public UpdatedProductProductOfferResponse update(@Valid @RequestBody UpdateProductProductOfferRequest updateProductProductOfferRequest, @PathVariable String id) {
        return productProductOfferService.update(updateProductProductOfferRequest, id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getAll")
    public List<GetAllProductProductOfferResponse> getAll() {
        return productProductOfferService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getById")
    public GetProductProductOfferResponse getById(@PathVariable  String id) {
        return productProductOfferService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete")
    @ResponseStatus(HttpStatus.OK)
    public DeletedProductProductOfferResponse delete(@PathVariable  String id) {
        return productProductOfferService.delete(id);
    }
}
