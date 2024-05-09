package com.etiya.common.events.customers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent {
    private long id;
    private String firstName;
}