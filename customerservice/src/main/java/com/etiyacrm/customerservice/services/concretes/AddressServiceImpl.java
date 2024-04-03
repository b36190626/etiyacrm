package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.repositories.AddressRepository;
import com.etiyacrm.customerservice.services.abstracts.AddressService;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.*;
import com.etiyacrm.customerservice.services.mappers.AddressMapper;
import com.etiyacrm.customerservice.services.rules.AddressBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressBusinessRules addressBusinessRules;

    @Override
    public List<GetAllAddressResponse> getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Address> response =  addressRepository.findAll(pageable);

        return response.map(address -> AddressMapper.INSTANCE.getAllAddressResponse(address)).getContent();
    }

    @Override
    public GetAddressResponse getById(long id) {
        Address address = addressRepository.findById(id).get();
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        GetAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {

        Address city = AddressMapper.INSTANCE.addressFromCreateAddressRequest(createAddressRequest);
        Address createdAddress = addressRepository.save(city);

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
}
