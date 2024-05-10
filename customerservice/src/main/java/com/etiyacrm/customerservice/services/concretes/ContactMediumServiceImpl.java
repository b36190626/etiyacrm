package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.ContactMedium;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.repositories.ContactMediumRepository;
import com.etiyacrm.customerservice.services.abstracts.ContactMediumService;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.CreateContactMediumRequest;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.UpdateContactMediumRequest;
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

    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest createContactMediumRequest) {
        contactMediumBussinessRules.checkIfCustomerHasContactMedium(createContactMediumRequest.getCustomerId());

        ContactMedium contactMedium =
                ContactMediumMapper.INSTANCE.contactMediumFromCreateContactMediumRequest(createContactMediumRequest);
        Customer customer = new Customer();
        customer.setId(createContactMediumRequest.getCustomerId());
        contactMedium.setCustomer(customer);
        ContactMedium createdContactMedium = contactMediumRepository.save(contactMedium);

        CreatedContactMediumResponse createdContactMediumResponse =
                ContactMediumMapper.INSTANCE.createdContactMediumResponseFromContactMedium(createdContactMedium);
        createdContactMediumResponse.setId(createdContactMedium.getId());
        createdContactMediumResponse.setCustomerId(createdContactMedium.getCustomer().getId());
        return createdContactMediumResponse;
    }

    @Override
    public UpdatedContactMediumResponse update(String id, UpdateContactMediumRequest updateContactMediumRequest) {

        ContactMedium findContactMedium = this.contactMediumRepository.findById(id).get();
        ContactMedium contactMedium =
                ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(updateContactMediumRequest);
        contactMedium.setId(id);
        contactMedium.setUpdatedDate(LocalDateTime.now());
        Customer customer = new Customer();
        customer.setId(findContactMedium.getCustomer().getId());
        contactMedium.setCustomer(customer);
        ContactMedium updatedContactmedium = contactMediumRepository.save(contactMedium);

        UpdatedContactMediumResponse updatedContactMediumResponse =
                ContactMediumMapper.INSTANCE.updatedContactMediumResponseFromContactMedium(updatedContactmedium);
        updatedContactMediumResponse.setId(updatedContactmedium.getId());
        updatedContactMediumResponse.setCustomerId(updatedContactmedium.getCustomer().getId());

        return updatedContactMediumResponse;
    }

    @Override
    public GetContactMediumResponse getById(String id) {
        contactMediumBussinessRules.checkIfContactMedium(id);
        ContactMedium contactMedium = contactMediumRepository.findByCustomerId(id).get();
        GetContactMediumResponse contactMediumResponse = ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
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
