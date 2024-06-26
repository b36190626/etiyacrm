package com.etiyacrm.customerservice.services.concretes;


import com.etiya.common.events.customers.CustomerDeletedEvent;
import com.etiya.common.events.customers.CustomerUpdatedEvent;
import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.kafka.producers.customers.CustomerProducer;
import com.etiyacrm.customerservice.repositories.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.RealCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.individualCustomerResponses.*;
import com.etiyacrm.customerservice.services.mappers.IndividualCustomerMapper;
import com.etiyacrm.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private CustomerProducer customerProducer;
    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(createIndividualCustomerRequest.getNationalityIdentity());
        individualCustomerBusinessRules.checkIfNationalIdentityExists(
                createIndividualCustomerRequest.getNationalityIdentity(),
                createIndividualCustomerRequest.getFirstName(),
                createIndividualCustomerRequest.getMiddleName(),
                createIndividualCustomerRequest.getLastName(),
                createIndividualCustomerRequest.getBirthDate().getYear());

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE
                .individualCustomerFromCreateIndividualCustomerRequest(createIndividualCustomerRequest);

        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);
        createdIndividualCustomerResponse.setId(createdIndividualCustomer.getId());

        return createdIndividualCustomerResponse;
    }
    @Override
    public PageInfoResponse<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<IndividualCustomer> response =  individualCustomerRepository.findAll(pageable);
        Page<GetAllIndividualCustomerResponse> responsePage = response
                .map(individualCustomer -> IndividualCustomerMapper.INSTANCE.getAllIndividualCustomerFromIndividualCustomer(individualCustomer));
        return new PageInfoResponse<>(responsePage);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, String id) throws Exception {
        individualCustomerBusinessRules.checkIfIndividualCustomer(id);
        individualCustomerBusinessRules.checkIfRealCustomerExist(
                updateIndividualCustomerRequest.getNationalityIdentity(),
                updateIndividualCustomerRequest.getFirstName(),
                updateIndividualCustomerRequest.getMiddleName(),
                updateIndividualCustomerRequest.getLastName(),
                updateIndividualCustomerRequest.getBirthDate());
        String confirmedNationalityIdentity = individualCustomerBusinessRules.checkIfIndividualCustomerCanUpdate(id, updateIndividualCustomerRequest.getNationalityIdentity());
        IndividualCustomer individualCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(updateIndividualCustomerRequest);
        individualCustomer.setId(id);
        individualCustomer.setNationalityIdentity(confirmedNationalityIdentity);
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);
        updatedIndividualCustomerResponse.setId(updatedIndividualCustomer.getId());


        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        customerUpdatedEvent.setNationalityIdentity(updatedIndividualCustomer.getNationalityIdentity());
        customerUpdatedEvent.setId(updatedIndividualCustomer.getId());
        customerUpdatedEvent.setFirstName(updatedIndividualCustomer.getFirstName());
        customerUpdatedEvent.setMiddleName(updatedIndividualCustomer.getMiddleName());
        customerUpdatedEvent.setLastName(updatedIndividualCustomer.getLastName());
        customerProducer.sendMessage(customerUpdatedEvent);

        return updatedIndividualCustomerResponse;
    }
    @Override
    public GetIndividualCustomerResponse getById(String id) {
        individualCustomerBusinessRules.checkIfIndividualCustomer(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        individualCustomerBusinessRules.checkIfIndividualCustomerDeleted(individualCustomer.getDeletedDate());

        GetIndividualCustomerResponse getIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomer(individualCustomer);
        getIndividualCustomerResponse.setId(individualCustomer.getId());

        return getIndividualCustomerResponse;
    }

    @Override
    public DeletedIndividualCustomerResponse delete(String id) {
        individualCustomerBusinessRules.checkIfIndividualCustomer(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        individualCustomerBusinessRules.checkIfIndividualCustomerDeleted(individualCustomer.getDeletedDate());

        individualCustomer.setDeletedDate(LocalDateTime.now());
        IndividualCustomer deletedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        DeletedIndividualCustomerResponse deletedIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.deletedIndividualCustomerResponseFromIndividualCustomer(deletedIndividualCustomer);
        deletedIndividualCustomerResponse.setId(deletedIndividualCustomer.getId());

        CustomerDeletedEvent customerDeletedEvent = new CustomerDeletedEvent();
        customerDeletedEvent.setId(deletedIndividualCustomer.getId());
        customerDeletedEvent.setDeletedDate(deletedIndividualCustomer.getDeletedDate());
        customerProducer.sendMessage(customerDeletedEvent);

        return deletedIndividualCustomerResponse;
    }

    @Override
    public boolean checkIfCustomerDuplicated(String nationalityIdentity) {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(nationalityIdentity);
        return true;
    }

    @Override
    public boolean checkIfCustomerReal(String nationalityIdentity,
                                       String firstName,
                                       String middleName,
                                       String lastName,
                                       LocalDate birthDate) throws Exception {
       individualCustomerBusinessRules.checkIfRealCustomerExist(
               nationalityIdentity, firstName, middleName, lastName, birthDate);
       return true;
    }

}
