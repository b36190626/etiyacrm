package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.core.business.abstracts.MessageService;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.repositories.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.messages.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private IndividualCustomerRepository individualCustomerRepository;
    private MessageService messageService;

    public void nationalityIdentityCannotBeDuplicated(String nationalityIdentity){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository
                .findByNationalityIdentity(nationalityIdentity);
        if (individualCustomer.isPresent()){
            throw new BusinessException("A customer is already exist with this Nationality ID");
        }
    }

    public void checkIfIndividualCustomerDeleted(LocalDateTime deletedDate){
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findByDeletedDate(deletedDate);
        for (IndividualCustomer individualCustomer : individualCustomers){
            if (individualCustomer.getDeletedDate()!= null){
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.IndividualCustomerDeleted));
            }
        }
    }

}
