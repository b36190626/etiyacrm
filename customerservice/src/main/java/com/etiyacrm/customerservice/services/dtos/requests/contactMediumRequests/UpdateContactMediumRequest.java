package com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateContactMediumRequest {
    @NotNull
    private String email;
    @NotNull
    private String mobilePhone;

    private String homePhone;

    private String fax;

}
