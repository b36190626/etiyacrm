package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.repositories.AddressRepository;
import com.etiyacrm.customerservice.services.abstracts.AddressService;
import com.etiyacrm.customerservice.services.abstracts.CityService;
import com.etiyacrm.customerservice.services.abstracts.CustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.*;
import com.etiyacrm.customerservice.services.dtos.responses.cityresponses.GetAllCityResponse;
import com.etiyacrm.customerservice.services.mappers.AddressMapper;
import com.etiyacrm.customerservice.services.mappers.CityMapper;
import com.etiyacrm.customerservice.services.rules.AddressBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressBusinessRules addressBusinessRules;


    @Override
    public PageInfoResponse<GetAllAddressResponse> getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Address> response =  addressRepository.findByDeletedDateIsNull(pageable);
        Page<GetAllAddressResponse> responsePage = response
                .map(address -> AddressMapper.INSTANCE.getAllAddressResponse(address));

        return new PageInfoResponse<>(responsePage);
    }

    @Override
    public GetAddressResponse getById(long id) {
        Address address = addressRepository.findById(id).get();
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        GetAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }
//AddressMapper.INSTANCE.addressFromCreateAddressRequest(createAddressRequest);
    @Override
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {
        City city = new City();
        city.setId(createAddressRequest.getCityId());
        Customer customer = new Customer();
        customer.setId(createAddressRequest.getCustomerId());

        Address address = new Address();
        address.setDescription(createAddressRequest.getDescription());
        address.setDistrict(createAddressRequest.getDistrict());
        address.setFlatNumber(createAddressRequest.getFlatNumber());
        address.setStreet(createAddressRequest.getStreet());
        address.setCreatedDate(LocalDateTime.now());
        address.setCity(city);
        address.setCustomer(customer);
        Address createdAddress = addressRepository.save(address);

        CreatedAddressResponse createdAddressResponse = AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
        return  createdAddressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest updateAddressRequest, long id) {
        Address address = AddressMapper.INSTANCE.addressFromUpdateAddressRequest(updateAddressRequest);
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        address.setId(id);
        address.setUpdatedDate(LocalDateTime.now());
        Address updatedAddress = addressRepository.save(address);

        UpdatedAddressResponse updatedAddressResponse = AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
        updatedAddressResponse.setCityId(updateAddressRequest.getCityId());
        updatedAddressResponse.setCustomerId(updateAddressRequest.getCustomerId());
        return updatedAddressResponse;
    }

    @Override
    public DeletedAddressResponse delete(long id) {
        Address address = addressRepository.findById(id).get();
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        address.setId(id);
        address.setDeletedDate(LocalDateTime.now());
        Address deletedAddress = addressRepository.save(address);

        DeletedAddressResponse deletedAddressResponse = AddressMapper.INSTANCE.deletedAddressResponseFromAddress(deletedAddress);
        deletedAddressResponse.setDeletedDate(deletedAddress.getDeletedDate());
        return deletedAddressResponse;
    }

    @Override
    public List<GetAllAddressResponse> getAllAddresses() {

        List<Address> response =  addressRepository.findAll();
        List<GetAllAddressResponse> responsePage = response.stream()
                .map(address -> AddressMapper.INSTANCE.getAllListAddressResponse(address))
                .collect(Collectors.toList());
        return responsePage;
    }
}
