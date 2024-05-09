package com.etiyacrm.customerservice.services.concretes;

import com.etiya.common.events.customers.CustomerCreatedEvent;
import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.kafka.producers.CustomerProducer;
import com.etiyacrm.customerservice.repositories.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.abstracts.CustomerService;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.*;
import com.etiyacrm.customerservice.services.mappers.IndividualCustomerMapper;
import com.etiyacrm.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private CustomerService customerService;
    private CustomerProducer customerProducer;

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(createIndividualCustomerRequest.getNationalityIdentity());
        Customer customer = new Customer();
        customerService.add(customer);

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE
                .individualCustomerFromCreateIndividualCustomerRequest(createIndividualCustomerRequest);
        individualCustomer.setCustomer(customer);

        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);


        CreatedIndividualCustomerResponse createdIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerProducer.sendMessage(customerCreatedEvent);
        return createdIndividualCustomerResponse;
    }
    @Override
    public PageInfoResponse<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<IndividualCustomer> response =  individualCustomerRepository.findByDeletedDateIsNull(pageable);
        Page<GetAllIndividualCustomerResponse> responsePage = response
                .map(individualCustomer -> IndividualCustomerMapper.INSTANCE.getAllIndividualCustomerFromIndividualCustomer(individualCustomer));
        return new PageInfoResponse<>(responsePage);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id) {
        individualCustomerBusinessRules.checkIfIndividualCustomer(id);

        Optional<IndividualCustomer> optionalIndividualCustomer = individualCustomerRepository.findById(id);

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(updateIndividualCustomerRequest);
        individualCustomer.setId(id);
        individualCustomer.setCustomer(optionalIndividualCustomer.get().getCustomer());
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        Customer foundCustomer = customerService.getById(updatedIndividualCustomer.getCustomer().getId());
        foundCustomer.setUpdatedDate(updatedIndividualCustomer.getUpdatedDate());
        Customer updatedCustomer = customerService.add(foundCustomer);
        updatedIndividualCustomer.setCustomer(updatedCustomer);

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);

        return updatedIndividualCustomerResponse;
    }
    @Override
    public GetIndividualCustomerResponse getById(long id) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        individualCustomerBusinessRules.checkIfIndividualCustomerDeleted(individualCustomer.getDeletedDate());
        GetIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomer(individualCustomer);
        return response;
    }

    @Override
    public DeletedIndividualCustomerResponse delete(long id) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        individualCustomerBusinessRules.checkIfIndividualCustomerDeleted(individualCustomer.getDeletedDate());
        individualCustomer.setId(id);
        individualCustomer.setDeletedDate(LocalDateTime.now());
        IndividualCustomer deletedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        DeletedIndividualCustomerResponse deletedIndividualCustomerResponse = IndividualCustomerMapper.INSTANCE.deletedIndividualCustomerResponseFromIndividualCustomer(individualCustomer);
        deletedIndividualCustomerResponse.setDeletedDate(deletedIndividualCustomer.getDeletedDate());
        return deletedIndividualCustomerResponse;
    }
}
