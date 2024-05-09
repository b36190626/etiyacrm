package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.ContactMedium;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.CreateContactMediumRequest;
import com.etiyacrm.customerservice.services.dtos.requests.contactMediumRequests.UpdateContactMediumRequest;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.CreatedContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.DeletedContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.GetContactMediumResponse;
import com.etiyacrm.customerservice.services.dtos.responses.contactMediumResponses.UpdatedContactMediumResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-09T14:59:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
public class ContactMediumMapperImpl implements ContactMediumMapper {

    @Override
    public CreatedContactMediumResponse createdContactMediumResponseFromContactMedium(ContactMedium contactMedium) {
        if ( contactMedium == null ) {
            return null;
        }

        CreatedContactMediumResponse createdContactMediumResponse = new CreatedContactMediumResponse();

        createdContactMediumResponse.setEmail( contactMedium.getEmail() );
        createdContactMediumResponse.setMobilePhone( contactMedium.getMobilePhone() );
        createdContactMediumResponse.setHomePhone( contactMedium.getHomePhone() );
        createdContactMediumResponse.setFax( contactMedium.getFax() );

        return createdContactMediumResponse;
    }

    @Override
    public UpdatedContactMediumResponse updatedContactMediumResponseFromContactMedium(ContactMedium contactMedium) {
        if ( contactMedium == null ) {
            return null;
        }

        UpdatedContactMediumResponse updatedContactMediumResponse = new UpdatedContactMediumResponse();

        updatedContactMediumResponse.setEmail( contactMedium.getEmail() );
        updatedContactMediumResponse.setMobilePhone( contactMedium.getMobilePhone() );
        updatedContactMediumResponse.setHomePhone( contactMedium.getHomePhone() );
        updatedContactMediumResponse.setFax( contactMedium.getFax() );

        return updatedContactMediumResponse;
    }

    @Override
    public GetContactMediumResponse getContactMediumResponseFromContactMedium(ContactMedium contactMedium) {
        if ( contactMedium == null ) {
            return null;
        }

        GetContactMediumResponse getContactMediumResponse = new GetContactMediumResponse();

        getContactMediumResponse.setEmail( contactMedium.getEmail() );
        getContactMediumResponse.setMobilePhone( contactMedium.getMobilePhone() );
        getContactMediumResponse.setHomePhone( contactMedium.getHomePhone() );
        getContactMediumResponse.setFax( contactMedium.getFax() );

        return getContactMediumResponse;
    }

    @Override
    public DeletedContactMediumResponse deletedContactMediumResponseFromContactMedium(ContactMedium contactMedium) {
        if ( contactMedium == null ) {
            return null;
        }

        DeletedContactMediumResponse deletedContactMediumResponse = new DeletedContactMediumResponse();

        deletedContactMediumResponse.setId( contactMedium.getId() );
        deletedContactMediumResponse.setDeletedDate( contactMedium.getDeletedDate() );

        return deletedContactMediumResponse;
    }

    @Override
    public ContactMedium contactMediumFromCreateContactMediumRequest(CreateContactMediumRequest createContactMediumRequest) {
        if ( createContactMediumRequest == null ) {
            return null;
        }

        ContactMedium contactMedium = new ContactMedium();

        contactMedium.setEmail( createContactMediumRequest.getEmail() );
        contactMedium.setMobilePhone( createContactMediumRequest.getMobilePhone() );
        contactMedium.setHomePhone( createContactMediumRequest.getHomePhone() );
        contactMedium.setFax( createContactMediumRequest.getFax() );

        return contactMedium;
    }

    @Override
    public ContactMedium contactMediumFromUpdateContactMediumRequest(UpdateContactMediumRequest updateContactMediumRequest) {
        if ( updateContactMediumRequest == null ) {
            return null;
        }

        ContactMedium contactMedium = new ContactMedium();

        contactMedium.setEmail( updateContactMediumRequest.getEmail() );
        contactMedium.setMobilePhone( updateContactMediumRequest.getMobilePhone() );
        contactMedium.setHomePhone( updateContactMediumRequest.getHomePhone() );
        contactMedium.setFax( updateContactMediumRequest.getFax() );

        return contactMedium;
    }
}
