package com.etiyacrm.customerservice.services.dtos.requests.addressRequests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAddressRequest {
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 50)
    private String description;

    @NotNull
    private String street;

    @NotNull
    private int flatNumber;

    @NotNull
    private long cityId;

    @NotNull
    private long districtId;

    @NotNull
    private long customerId;
}
