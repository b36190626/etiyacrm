package com.etiyacrm.customerservice.business.dtos.responses.IndividualCustomerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedIndividualCustomerResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String nationalityIdentityCard;
}
