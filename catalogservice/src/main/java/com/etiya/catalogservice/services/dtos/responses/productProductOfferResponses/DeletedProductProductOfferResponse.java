package com.etiya.catalogservice.services.dtos.responses.productProductOfferResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedProductProductOfferResponse {
    private String id;
    private LocalDate deletedDate;
}
