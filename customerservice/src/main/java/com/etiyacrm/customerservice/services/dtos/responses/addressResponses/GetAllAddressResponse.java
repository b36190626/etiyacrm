package com.etiyacrm.customerservice.services.dtos.responses.addressResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAddressResponse {
    private long id;
    private String cityName;
    private String description;
    private long cityId;
    private long customerId;
}
