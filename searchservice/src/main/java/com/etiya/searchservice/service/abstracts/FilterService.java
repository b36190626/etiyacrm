package com.etiya.searchservice.service.abstracts;

import com.etiya.searchservice.entities.Customer;

public interface FilterService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);

    Customer searchByNationalityIdentity(String nationalityIdentity);
}
