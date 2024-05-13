package com.etiya.searchservice.service.abstracts;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.dtos.responses.SearchResponse;

import java.util.List;

public interface FilterService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);

    List<SearchResponse> search(
            String nationalityIdentity, String id, String accountNumber,
            String mobilePhone, String firstName, String lastName, String orderNumber
    );
}
