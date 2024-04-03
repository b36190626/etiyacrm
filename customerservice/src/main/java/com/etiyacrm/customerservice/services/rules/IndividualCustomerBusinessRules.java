package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.repositories.IndividualCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private IndividualCustomerRepository individualCustomerRepository;

    public void nationalityIdentityCannotBeDuplicated(String natioanlityIdentity){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository
                .findByNationalityIdIgnoreCase(natioanlityIdentity);
        if (individualCustomer.isPresent()){
            throw new BusinessException("A customer is already exist with this Nationality ID");
        }

    }

}
