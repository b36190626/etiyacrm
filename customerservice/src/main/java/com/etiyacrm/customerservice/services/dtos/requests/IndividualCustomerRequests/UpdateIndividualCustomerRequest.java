package com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 30)
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    private String fatherName;

    private String motherName;

    @NotEmpty(message = "Nationality ID cannot be empty")
    @Pattern(regexp = "\\d{11}")
    private String nationalityIdentity;
}
