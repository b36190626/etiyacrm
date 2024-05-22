package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.adapters.CustomerCheckService;
import com.etiyacrm.customerservice.adapters.MernisServiceAdapter;
import com.etiyacrm.customerservice.core.business.abstracts.MessageService;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.repositories.CustomerRepository;
import com.etiyacrm.customerservice.repositories.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.RealCustomerRequest;
import com.etiyacrm.customerservice.services.messages.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private IndividualCustomerRepository individualCustomerRepository;
    private MessageService messageService;
    private CustomerCheckService customerCheckService;

    public void nationalityIdentityCannotBeDuplicated(String nationalityIdentity){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository
                .findByNationalityIdentity(nationalityIdentity);
        if (individualCustomer.isPresent()){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NationalityIdentityExists));
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
    public void checkIfIndividualCustomer(String id){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findById(id);
        if (individualCustomer.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.IndividualCustomerIdNotExists));
        }
    }

    public String checkIfIndividualCustomerCanUpdate(String id ,String nationalityIdentity){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findById(id);

        if (!individualCustomer.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.IndividualCustomerIdNotExists));
        }

        IndividualCustomer existingCustomer = individualCustomer.get();

        Optional<IndividualCustomer> customerWithSameNationalityIdentity = individualCustomerRepository.findByNationalityIdentity(nationalityIdentity);
        if (customerWithSameNationalityIdentity.isPresent() && !customerWithSameNationalityIdentity.get().getId().equals(existingCustomer.getId())) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NationalityIdentityExists));
        }
        return nationalityIdentity;
    }

    public void checkIfNationalIdentityExists(String nationalityIdentity,
                                              String firstName,
                                              String middleName,
                                              String lastName,
                                              int birthDate) throws Exception {
        String convertedFirstName;
        if (middleName != null){
            convertedFirstName = firstName + " " + middleName;
        }
        else {
            convertedFirstName = firstName;
        }


        if (!customerCheckService.checkIfRealPerson(nationalityIdentity, convertedFirstName, lastName, birthDate)){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.IdentityNumberNotExists));
        }
    }

    public void checkIfRealCustomerExist(String nationalityIdentity,
                                         String firstName,
                                         String middleName,
                                         String lastName,
                                         LocalDate birthDate) throws Exception {
        String convertedFirstName;
        if(middleName != null){
            convertedFirstName = firstName + " " + middleName;
        }
        else {
            convertedFirstName = firstName;
        }

        int convertedBirthDate = birthDate.getYear();
        if (!customerCheckService.checkIfRealPerson(
                nationalityIdentity,
                convertedFirstName,
                lastName,
                convertedBirthDate
        )){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.IdentityNumberNotExists));
        }
    }
}
