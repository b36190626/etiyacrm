package com.etiya.common.events.customers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent {
    private String nationalityIdentity;
    private String id;
    private String firstName;
    private String accountNumber;
    private String mobilePhone;
    private String middleName;
    private String lastName;
    private String orderNumber;
}

