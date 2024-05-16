package com.etiya.catalogservice.services.concretes;

import com.etiya.catalogservice.entities.ProductProductOffer;
import com.etiya.catalogservice.repositories.ProductProductOfferRepository;
import com.etiya.catalogservice.services.abstracts.ProductProductOfferService;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.CreateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.UpdateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses.*;
import com.etiya.catalogservice.services.mappers.ProductProductOfferMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductProductOfferServiceImpl implements ProductProductOfferService {
    private ProductProductOfferRepository productProductOfferRepository;
    @Override
    public CreatedProductProductOfferResponse add(CreateProductProductOfferRequest createProductProductOfferRequest) {
        ProductProductOffer productProductOffer =
                ProductProductOfferMapper.INSTANCE.productProductOfferFromCreateProductProductOfferRequest(createProductProductOfferRequest);

        ProductProductOffer createdProductProductOffer = productProductOfferRepository.save(productProductOffer);

        CreatedProductProductOfferResponse createdProductProductOfferResponse =
                ProductProductOfferMapper.INSTANCE.createdProductProductOfferResponseFromProductProductOffer(createdProductProductOffer);

        return createdProductProductOfferResponse;
    }

    @Override
    public UpdatedProductProductOfferResponse update(UpdateProductProductOfferRequest updateProductProductOfferRequest, String id) {
        ProductProductOffer productProductOffer =
                ProductProductOfferMapper.INSTANCE.productProductOfferFromUpdateProductProductOfferRequest(updateProductProductOfferRequest);
        productProductOffer.setId(id);
        productProductOffer.setUpdatedDate(LocalDateTime.now());
        ProductProductOffer updatedProductProductOffer = productProductOfferRepository.save(productProductOffer);

        UpdatedProductProductOfferResponse updatedProductProductOfferResponse =
                ProductProductOfferMapper.INSTANCE.updatedProductProductOfferResponseFromProductProductOffer(updatedProductProductOffer);

        return updatedProductProductOfferResponse;
    }

    @Override
    public List<GetAllProductProductOfferResponse> getAll() {
        List<ProductProductOffer> productProductOffers = productProductOfferRepository.findAll();

        List<GetAllProductProductOfferResponse> getAllProductProductOfferResponses = productProductOffers.stream()
                .map(ProductProductOfferMapper.INSTANCE::getAllProductProductOfferResponseFromProductProductOffer)
                .collect(Collectors.toList());

        return getAllProductProductOfferResponses;
    }

    @Override
    public GetProductProductOfferResponse getById(String id) {
        ProductProductOffer productProductOffer = productProductOfferRepository.findById(id).get();

        GetProductProductOfferResponse getProductProductOfferResponse =
                ProductProductOfferMapper.INSTANCE.getProductProductOfferResponseFromProductProductOffer(productProductOffer);

        return getProductProductOfferResponse;
    }

    @Override
    public DeletedProductProductOfferResponse delete(String id) {
        ProductProductOffer productProductOffer = productProductOfferRepository.findById(id).get();
        productProductOffer.setId(id);
        productProductOffer.setDeletedDate(LocalDateTime.now());
        ProductProductOffer deletedProductProductOffer = productProductOfferRepository.save(productProductOffer);

        DeletedProductProductOfferResponse deletedProductProductOfferResponse =
                ProductProductOfferMapper.INSTANCE.deletedProductProductOfferResponseFromProductProductOffer(deletedProductProductOffer);

        return deletedProductProductOfferResponse;
    }
}
