package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "customer.id", target = "customerId")
    GetAllAddressResponse getAllAddressResponse(Address address);
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "city.name", target = "cityName")
//    @Mapping(source = "district.id", target = "districtId")
//    @Mapping(source = "district.name", target = "districtName")
    GetAllAddressResponse getAllListAddressResponse(Address address);

    Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest);
    CreatedAddressResponse createdAddressResponseFromAddress(Address address);
    Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest);
    UpdatedAddressResponse updatedAddressResponseFromAddress(Address address);
    DeletedAddressResponse deletedAddressResponseFromAddress(Address address);
    GetAddressResponse getAddressResponseFromAddress(Address address);

}
