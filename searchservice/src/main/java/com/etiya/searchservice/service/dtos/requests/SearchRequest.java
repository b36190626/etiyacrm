package com.etiya.searchservice.service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String nationalityIdentity;
    private String id;
    private String accountNumber;
    private String mobilePhone;
    private String firstName;
    private String lastName;
    private String orderNumber;
}
