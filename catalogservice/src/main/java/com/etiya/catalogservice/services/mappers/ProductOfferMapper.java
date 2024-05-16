package com.etiya.catalogservice.services.mappers;

import com.etiya.catalogservice.entities.ProductOffer;
import com.etiya.catalogservice.services.dtos.requests.productOfferRequests.CreateProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.productOfferRequests.UpdateProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.productOfferResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductOfferMapper {
    ProductOfferMapper INSTANCE = Mappers.getMapper(ProductOfferMapper.class);
    GetAllProductOfferResponse getAllProductOfferResponseFromProductOffer(ProductOffer productOffer);
    ProductOffer productOfferFromCreateProductOfferRequest(CreateProductOfferRequest createProductOfferRequest);
    CreatedProductOfferResponse createdProductOfferResponseFromProductOffer(ProductOffer productOffer);
    ProductOffer productOfferFromUpdateProductOfferRequest(UpdateProductOfferRequest updateProductOfferRequest);
    UpdatedProductOfferResponse updatedProductOfferResponseFromProductOffer(ProductOffer productOffer);
    DeletedProductOfferResponse deletedProductOfferResponseFromProductOffer(ProductOffer productOffer);
    GetProductOfferResponse getProductOfferResponseFromCProductOffer(ProductOffer productOffer);
}
