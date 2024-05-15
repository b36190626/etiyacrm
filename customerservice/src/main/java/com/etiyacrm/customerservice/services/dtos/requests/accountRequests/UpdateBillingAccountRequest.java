package com.etiyacrm.customerservice.services.dtos.requests.accountRequests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBillingAccountRequest {

    @NotBlank
    private String name;
}
