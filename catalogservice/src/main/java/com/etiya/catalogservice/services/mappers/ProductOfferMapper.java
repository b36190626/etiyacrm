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

    //@Mapping(source = "customer.id", target = "customerId")
    GetAllProductOfferResponse getAllProductOfferResponseFromProductOffer(ProductOffer productOffer);

    //@Mapping(source = "customerId", target = "customer.id")
    //@Mapping(source = "districtId", target = "district.id")
    ProductOffer productOfferFromCreateProductOfferRequest(CreateProductOfferRequest createProductOfferRequest);
    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    CreatedProductOfferResponse createdProductOfferResponseFromProductOffer(ProductOffer productOffer);
    ProductOffer productOfferFromUpdateProductOfferRequest(UpdateProductOfferRequest updateProductOfferRequest);
    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    UpdatedProductOfferResponse updatedProductOfferResponseFromProductOffer(ProductOffer productOffer);
    DeletedProductOfferResponse deletedProductOfferResponseFromProductOffer(ProductOffer productOffer);

    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    GetProductOfferResponse getProductOfferResponseFromCProductOffer(ProductOffer productOffer);
}
