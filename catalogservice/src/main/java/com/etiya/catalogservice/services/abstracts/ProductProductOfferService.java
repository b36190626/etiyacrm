package com.etiya.catalogservice.services.abstracts;

import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.CreateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.productProductOfferRequests.UpdateProductProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses.*;

import java.util.List;

public interface ProductProductOfferService {
    CreatedProductProductOfferResponse add(CreateProductProductOfferRequest createProductProductOfferRequest);
    UpdatedProductProductOfferResponse update(UpdateProductProductOfferRequest updateProductProductOfferRequest, String id);
    List<GetAllProductProductOfferResponse> getAll();
    GetProductProductOfferResponse getById(String id);
    DeletedProductProductOfferResponse delete(String id);
}
