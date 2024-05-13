package com.etiya.searchservice.service.concretes;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.repository.FilterRepository;
import com.etiya.searchservice.service.abstracts.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Customer> search(
            String nationalityIdentity,
            String id,
            String accountNumber,
            String mobilePhone,
            String firstName,
            String lastName,
            String orderNumber) {

        List<Customer> customers =
                this.filterRepository.searchResult(
                        nationalityIdentity,
                        id,
                        mobilePhone, accountNumber,
                        firstName,
                        lastName,
                        orderNumber
                );
        return customers;

    }
}
