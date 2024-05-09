package com.etiya.common.events.customers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent {
    //individual customer
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String nationalityIdentity;

    //contact medium
    private long contactMediumId;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
    private long customerId;

    //address
    private long addressId;
    private String description;
    private String street;
    private int flatNumber;
    private long cityId;
    private String cityName;
    private long districtId;
    private String districtName;
    //private long customerId;
}
