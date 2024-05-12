package com.etiya.searchservice.service.abstracts;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.dtos.requests.SearchRequest;
import com.etiya.searchservice.service.dtos.responses.SearchResponse;

public interface FilterService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);

}
