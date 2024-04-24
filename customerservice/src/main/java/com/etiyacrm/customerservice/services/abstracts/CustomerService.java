package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.entities.Customer;

public interface CustomerService {
    Customer getById(long id);

    Customer add(Customer customer);
}
