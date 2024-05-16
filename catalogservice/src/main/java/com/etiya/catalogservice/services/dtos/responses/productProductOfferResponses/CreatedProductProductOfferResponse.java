package com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedProductProductOfferResponse {
    private String id;
    private String productId;
    private String productOfferId;
}
