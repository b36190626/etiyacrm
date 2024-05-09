package com.etiyacrm.customerservice.services.dtos.responses.addressResponses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedAddressResponse {
    private String id;
    private String description;
    private String street;
    private int flatNumber;
    private String cityId;
    private String districtId;
    private String customerId;
}
