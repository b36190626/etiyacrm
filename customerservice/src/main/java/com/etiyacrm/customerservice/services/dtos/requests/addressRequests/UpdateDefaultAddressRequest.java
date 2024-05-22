package com.etiyacrm.customerservice.services.dtos.requests.addressRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDefaultAddressRequest {
    boolean defaultAddress;
}
