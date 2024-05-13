package com.etiya.searchservice.service.concretes;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.repository.FilterRepository;
import com.etiya.searchservice.service.abstracts.FilterService;
import com.etiya.searchservice.service.dtos.responses.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class FilterServiceImpl implements FilterService {
    private FilterRepository filterRepository;

    @Override
    public void addCustomer(Customer customer) {
        this.filterRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.filterRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        this.filterRepository.save(customer);
    }

    @Override
    public List<SearchResponse> search(
            String nationalityIdentity, String id, String mobilePhone,
            String accountNumber, String firstName, String lastName, String orderNumber) {

        List<Customer> customers =
                this.filterRepository.searchResult(
                        nationalityIdentity, id, mobilePhone, accountNumber, firstName, lastName, orderNumber
                );
        List<SearchResponse> searchResponses = new ArrayList<>();

        for (Customer customer : customers) {
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.setId(customer.getId());
            searchResponse.setFirstName(customer.getFirstName());
            searchResponse.setMiddleName(customer.getMiddleName());
            searchResponse.setLastName(customer.getLastName());
            searchResponse.setRole(customer.getRole());
            searchResponse.setNationalityIdentity(customer.getNationalityIdentity());
            searchResponse.setAccountNumber(customer.getAccountNumber());
            searchResponse.setMobilePhone(customer.getMobilePhone());

            searchResponses.add(searchResponse);
        }
        return searchResponses;
    }
}