package com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RealCustomerRequest {
    private String nationalityIdentity;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
}
