package com.etiyacrm.customerservice.services.concretes;

import com.etiya.common.events.customers.CustomerCreatedEvent;
import com.etiya.common.events.customers.CustomerUpdatedEvent;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.ContactMedium;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.kafka.producers.customers.CustomerProducer;
import com.etiyacrm.customerservice.repositories.ContactMediumRepository;
import com.etiyacrm.customerservice.services.abstracts.ContactMediumService;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.CreateContactMediumRequest;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.UpdateContactMediumRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.GetIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.CreatedContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.DeletedContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.GetContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.UpdatedContactMediumResponse;
import com.etiyacrm.customerservice.services.mappers.ContactMediumMapper;
import com.etiyacrm.customerservice.services.rules.ContactMediumBussinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ContactMediumServiceImpl implements ContactMediumService {
    private ContactMediumRepository contactMediumRepository;
    private ContactMediumBussinessRules contactMediumBussinessRules;
    private IndividualCustomerService individualCustomerService;
    private CustomerProducer customerProducer;

    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest createContactMediumRequest) {
        contactMediumBussinessRules.checkIfCustomerHasContactMedium(createContactMediumRequest.getCustomerId());

        ContactMedium contactMedium =
                ContactMediumMapper.INSTANCE.contactMediumFromCreateContactMediumRequest(createContactMediumRequest);

        ContactMedium createdContactMedium = contactMediumRepository.save(contactMedium);

        CreatedContactMediumResponse createdContactMediumResponse =
                ContactMediumMapper.INSTANCE.createdContactMediumResponseFromContactMedium(createdContactMedium);
        createdContactMediumResponse.setId(createdContactMedium.getId());
        createdContactMediumResponse.setCustomerId(createdContactMedium.getCustomer().getId());

        GetIndividualCustomerResponse individualCustomer = individualCustomerService.getById(createdContactMedium.getId());
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setNationalityIdentity(individualCustomer.getNationalityIdentity());
        customerCreatedEvent.setId(createdContactMedium.getId());
        customerCreatedEvent.setMobilePhone(createdContactMedium.getMobilePhone());
        customerCreatedEvent.setFirstName(individualCustomer.getFirstName());
        customerCreatedEvent.setLastName(individualCustomer.getLastName());
        customerProducer.sendMessage(customerCreatedEvent);
        return createdContactMediumResponse;
    }

    @Override
    public UpdatedContactMediumResponse update(String id, UpdateContactMediumRequest updateContactMediumRequest) {

        ContactMedium savedContactMedium = contactMediumRepository.findById(id).get();
        ContactMedium contactMedium =
                ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(updateContactMediumRequest);
        contactMedium.setId(id);
        contactMedium.setCustomer(savedContactMedium.getCustomer());
        contactMedium.setUpdatedDate(LocalDateTime.now());

        ContactMedium updatedContactmedium = contactMediumRepository.save(contactMedium);

        UpdatedContactMediumResponse updatedContactMediumResponse =
                ContactMediumMapper.INSTANCE.updatedContactMediumResponseFromContactMedium(updatedContactmedium);
        updatedContactMediumResponse.setId(updatedContactmedium.getId());

        GetIndividualCustomerResponse individualCustomer =
                individualCustomerService.getById(savedContactMedium.getCustomer().getId());
        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        customerUpdatedEvent.setNationalityIdentity(individualCustomer.getNationalityIdentity());
        customerUpdatedEvent.setId(updatedContactmedium.getId());
        customerUpdatedEvent.setMobilePhone(updatedContactmedium.getMobilePhone());
        customerUpdatedEvent.setFirstName(individualCustomer.getFirstName());
        customerUpdatedEvent.setLastName(individualCustomer.getLastName());
        customerProducer.sendMessage(customerUpdatedEvent);

        return updatedContactMediumResponse;
    }

    @Override
    public GetContactMediumResponse getById(String id) {
        contactMediumBussinessRules.checkIfContactMedium(id);

        ContactMedium contactMedium = contactMediumRepository.findByCustomerId(id).get();
        GetContactMediumResponse contactMediumResponse =
                ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        contactMediumResponse.setCustomerId(contactMedium.getCustomer().getId());
        contactMediumResponse.setId(contactMedium.getId());
        return contactMediumResponse;
    }

    @Override
    public DeletedContactMediumResponse delete(String id) {

        ContactMedium contactMedium = contactMediumRepository.findById(id).get();
        contactMedium.setId(id);
        contactMedium.setDeletedDate(LocalDateTime.now());
        ContactMedium deletedContactMedium = contactMediumRepository.save(contactMedium);

        DeletedContactMediumResponse deletedContactMediumResponse = ContactMediumMapper.INSTANCE.deletedContactMediumResponseFromContactMedium(deletedContactMedium);
        deletedContactMediumResponse.setDeletedDate(deletedContactMedium.getDeletedDate());
        return deletedContactMediumResponse;
    }
}
