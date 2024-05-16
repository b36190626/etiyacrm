package com.etiya.catalogservice.services.mappers;

import com.etiya.catalogservice.entities.ProductProductOffer;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.CreateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.UpdateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductProductOfferMapper {
    ProductProductOfferMapper INSTANCE = Mappers.getMapper(ProductProductOfferMapper.class);

    @Mapping(source = "productOffer.id", target = "productOfferId")
    @Mapping(source = "product.id", target = "productId")
    GetAllProductProductOfferResponse getAllProductProductOfferResponseFromProductProductOffer(ProductProductOffer productProductOffer);
    @Mapping(source = "productOfferId", target = "productOffer.id")
    @Mapping(source = "productId", target = "product.id")
    ProductProductOffer productProductOfferFromCreateProductProductOfferRequest(CreateProductProductOfferRequest createProductProductOfferRequest);
    @Mapping(source = "productOffer.id", target = "productOfferId")
    @Mapping(source = "product.id", target = "productId")
    CreatedProductProductOfferResponse createdProductProductOfferResponseFromProductProductOffer(ProductProductOffer productProductOffer);
    @Mapping(source = "productOfferId", target = "productOffer.id")
    @Mapping(source = "productId", target = "product.id")
    ProductProductOffer productProductOfferFromUpdateProductProductOfferRequest(UpdateProductProductOfferRequest updateProductProductOfferRequest);
    @Mapping(source = "productOffer.id", target = "productOfferId")
    @Mapping(source = "product.id", target = "productId")
    UpdatedProductProductOfferResponse updatedProductProductOfferResponseFromProductProductOffer(ProductProductOffer productProductOffer);
    DeletedProductProductOfferResponse deletedProductProductOfferResponseFromProductProductOffer(ProductProductOffer productProductOffer);
    @Mapping(source = "productOffer.id", target = "productOfferId")
    @Mapping(source = "product.id", target = "productId")
    GetProductProductOfferResponse getProductProductOfferResponseFromProductProductOffer(ProductProductOffer productProductOffer);
}
