package com.etiya.basketservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String nationalityIdentity;
}
