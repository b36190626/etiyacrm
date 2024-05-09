package com.etiyacrm.customerservice.services.dtos.responses.addressResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAddressResponse {
    private long id;
    private String description;
    private String street;
    private int flatNumber;
    private long cityId;
    private String cityName;
    private long districtId;
    private long customerId;
}
