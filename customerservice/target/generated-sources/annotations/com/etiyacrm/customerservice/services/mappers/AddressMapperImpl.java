package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.CreatedAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.DeletedAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.GetAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.GetAllAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.UpdatedAddressResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-06T18:25:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public GetAllAddressResponse getAllAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        GetAllAddressResponse getAllAddressResponse = new GetAllAddressResponse();

        getAllAddressResponse.setCityId( addressCityId( address ) );
        getAllAddressResponse.setCustomerId( addressCustomerId( address ) );
        getAllAddressResponse.setId( address.getId() );
        getAllAddressResponse.setDescription( address.getDescription() );

        return getAllAddressResponse;
    }

    @Override
    public Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest) {
        if ( createAddressRequest == null ) {
            return null;
        }

        Address address = new Address();

        address.setDescription( createAddressRequest.getDescription() );

        return address;
    }

    @Override
    public CreatedAddressResponse createdAddressResponseFromAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        CreatedAddressResponse createdAddressResponse = new CreatedAddressResponse();

        createdAddressResponse.setId( address.getId() );
        createdAddressResponse.setDescription( address.getDescription() );

        return createdAddressResponse;
    }

    @Override
    public Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest) {
        if ( updateAddressRequest == null ) {
            return null;
        }

        Address address = new Address();

        address.setDescription( updateAddressRequest.getDescription() );

        return address;
    }

    @Override
    public UpdatedAddressResponse updatedAddressResponseFromAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        UpdatedAddressResponse updatedAddressResponse = new UpdatedAddressResponse();

        updatedAddressResponse.setId( address.getId() );
        updatedAddressResponse.setDescription( address.getDescription() );

        return updatedAddressResponse;
    }

    @Override
    public DeletedAddressResponse deletedAddressResponseFromAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        DeletedAddressResponse deletedAddressResponse = new DeletedAddressResponse();

        deletedAddressResponse.setId( address.getId() );
        deletedAddressResponse.setDeletedDate( address.getDeletedDate() );

        return deletedAddressResponse;
    }

    @Override
    public GetAddressResponse getAddressResponseFromAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        GetAddressResponse getAddressResponse = new GetAddressResponse();

        getAddressResponse.setId( address.getId() );
        getAddressResponse.setDescription( address.getDescription() );

        return getAddressResponse;
    }

    private long addressCityId(Address address) {
        if ( address == null ) {
            return 0L;
        }
        City city = address.getCity();
        if ( city == null ) {
            return 0L;
        }
        long id = city.getId();
        return id;
    }

    private long addressCustomerId(Address address) {
        if ( address == null ) {
            return 0L;
        }
        Customer customer = address.getCustomer();
        if ( customer == null ) {
            return 0L;
        }
        long id = customer.getId();
        return id;
    }
}
