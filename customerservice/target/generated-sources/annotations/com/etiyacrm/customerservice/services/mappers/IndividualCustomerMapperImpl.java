package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.GetAllIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-03T18:47:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
public class IndividualCustomerMapperImpl implements IndividualCustomerMapper {

    @Override
    public GetAllIndividualCustomerResponse getAllIndividualCustomerFromIndividualCustomer(IndividualCustomer individualCustomer) {
        if ( individualCustomer == null ) {
            return null;
        }

        GetAllIndividualCustomerResponse getAllIndividualCustomerResponse = new GetAllIndividualCustomerResponse();

        getAllIndividualCustomerResponse.setFirstName( individualCustomer.getFirstName() );
        getAllIndividualCustomerResponse.setMiddleName( individualCustomer.getMiddleName() );
        getAllIndividualCustomerResponse.setLastName( individualCustomer.getLastName() );
        getAllIndividualCustomerResponse.setGender( individualCustomer.getGender() );
        getAllIndividualCustomerResponse.setBirthDate( individualCustomer.getBirthDate() );
        getAllIndividualCustomerResponse.setMotherName( individualCustomer.getMotherName() );
        getAllIndividualCustomerResponse.setFatherName( individualCustomer.getFatherName() );
        getAllIndividualCustomerResponse.setNationalityIdentity( individualCustomer.getNationalityIdentity() );

        return getAllIndividualCustomerResponse;
    }

    @Override
    public IndividualCustomer individualCustomerFromCreateIndividualCustomerRequest(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        if ( createIndividualCustomerRequest == null ) {
            return null;
        }

        IndividualCustomer individualCustomer = new IndividualCustomer();

        individualCustomer.setFirstName( createIndividualCustomerRequest.getFirstName() );
        individualCustomer.setMiddleName( createIndividualCustomerRequest.getMiddleName() );
        individualCustomer.setLastName( createIndividualCustomerRequest.getLastName() );
        individualCustomer.setGender( createIndividualCustomerRequest.getGender() );
        individualCustomer.setMotherName( createIndividualCustomerRequest.getMotherName() );
        individualCustomer.setFatherName( createIndividualCustomerRequest.getFatherName() );
        individualCustomer.setNationalityIdentity( createIndividualCustomerRequest.getNationalityIdentity() );
        individualCustomer.setBirthDate( createIndividualCustomerRequest.getBirthDate() );

        return individualCustomer;
    }

    @Override
    public CreatedIndividualCustomerResponse createdIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer) {
        if ( individualCustomer == null ) {
            return null;
        }

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse = new CreatedIndividualCustomerResponse();

        createdIndividualCustomerResponse.setFirstName( individualCustomer.getFirstName() );
        createdIndividualCustomerResponse.setMiddleName( individualCustomer.getMiddleName() );
        createdIndividualCustomerResponse.setLastName( individualCustomer.getLastName() );
        createdIndividualCustomerResponse.setGender( individualCustomer.getGender() );
        createdIndividualCustomerResponse.setBirthDate( individualCustomer.getBirthDate() );
        createdIndividualCustomerResponse.setMotherName( individualCustomer.getMotherName() );
        createdIndividualCustomerResponse.setFatherName( individualCustomer.getFatherName() );
        createdIndividualCustomerResponse.setNationalityIdentity( individualCustomer.getNationalityIdentity() );

        return createdIndividualCustomerResponse;
    }

    @Override
    public IndividualCustomer individualCustomerFromUpdateIndividualCustomerRequest(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        if ( updateIndividualCustomerRequest == null ) {
            return null;
        }

        IndividualCustomer individualCustomer = new IndividualCustomer();

        individualCustomer.setFirstName( updateIndividualCustomerRequest.getFirstName() );
        individualCustomer.setMiddleName( updateIndividualCustomerRequest.getMiddleName() );
        individualCustomer.setLastName( updateIndividualCustomerRequest.getLastName() );
        individualCustomer.setGender( updateIndividualCustomerRequest.getGender() );
        individualCustomer.setMotherName( updateIndividualCustomerRequest.getMotherName() );
        individualCustomer.setFatherName( updateIndividualCustomerRequest.getFatherName() );
        individualCustomer.setNationalityIdentity( updateIndividualCustomerRequest.getNationalityIdentity() );
        individualCustomer.setBirthDate( updateIndividualCustomerRequest.getBirthDate() );

        return individualCustomer;
    }

    @Override
    public UpdatedIndividualCustomerResponse updatedIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer) {
        if ( individualCustomer == null ) {
            return null;
        }

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = new UpdatedIndividualCustomerResponse();

        updatedIndividualCustomerResponse.setFirstName( individualCustomer.getFirstName() );
        updatedIndividualCustomerResponse.setMiddleName( individualCustomer.getMiddleName() );
        updatedIndividualCustomerResponse.setLastName( individualCustomer.getLastName() );
        updatedIndividualCustomerResponse.setGender( individualCustomer.getGender() );
        updatedIndividualCustomerResponse.setBirthDate( individualCustomer.getBirthDate() );
        updatedIndividualCustomerResponse.setMotherName( individualCustomer.getMotherName() );
        updatedIndividualCustomerResponse.setFatherName( individualCustomer.getFatherName() );
        updatedIndividualCustomerResponse.setNationalityIdentity( individualCustomer.getNationalityIdentity() );

        return updatedIndividualCustomerResponse;
    }
}
