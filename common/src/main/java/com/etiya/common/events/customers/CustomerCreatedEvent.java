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
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String nationalityIdentity;

    //contact medium
    private String contactMediumId;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;
    private String customerId;

    //address
    private String addressId;
    private String description;
    private String street;
    private int flatNumber;
    private String cityId;
    private String cityName;
    private String districtId;
    private String districtName;
    //private long customerId;
}
