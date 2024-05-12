package com.etiya.searchservice.repository;

import com.etiya.searchservice.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilterRepository extends MongoRepository<Customer,String> {
    List<Customer> searchByNationalityIdentityOrIdOrAccountNumberOrMobilePhoneOrFirstNameOrLastNameOrOrderNumber(
            String nationalityIdentity, String id, String accountNumber,
            String mobilePhone, String firstName, String lastName, String orderNumber
    );
}
