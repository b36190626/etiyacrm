package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.repositories.AddressRepository;
import com.etiyacrm.customerservice.services.abstracts.AddressService;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateDefaultAddressRequest;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressBusinessRules addressBusinessRules;


    @Override
    public PageInfoResponse<GetAllAddressResponse> getAllPage(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Address> response =  addressRepository.findAll(pageable);
        Page<GetAllAddressResponse> responsePage = response
                .map(address -> AddressMapper.INSTANCE.getAllAddressResponse(address));
        return new PageInfoResponse<>(responsePage);
    }

    @Override
    public List<GetAllAddressResponse> getAll() {

        List<Address> response =  addressRepository.findAll();
        List<GetAllAddressResponse> responsePage = response.stream()
                .map(address -> AddressMapper.INSTANCE.getAllListAddressResponse(address))
                .collect(Collectors.toList());
        return responsePage;
    }

    @Override
    public List<GetAddressResponse> getById(String id) {
        List<Address> addresses = addressRepository.findByCustomerId(id);
        List<GetAddressResponse> getAddressResponseList =
                addresses.stream().map(AddressMapper.INSTANCE::getAddressResponseFromAddress).collect(Collectors.toList());

        return getAddressResponseList;
    }
    @Override
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {

        Address address =
                AddressMapper.INSTANCE.addressFromCreateAddressRequest(createAddressRequest);
        Address createdAddress = addressRepository.save(address);
        addressBusinessRules.cantDeleteLastAddress(createAddressRequest.getCustomerId());
        CreatedAddressResponse createdAddressResponse =
                AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);

        return  createdAddressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest updateAddressRequest, String id) {

        Address address =
                AddressMapper.INSTANCE.addressFromUpdateAddressRequest(updateAddressRequest);
        boolean setDefaultAddress =
                addressBusinessRules.checkIfOneAddress(updateAddressRequest.getCustomerId(), updateAddressRequest.isDefaultAddress());
        address.setDefaultAddress(setDefaultAddress);
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        address.setId(id);
        address.setUpdatedDate(LocalDateTime.now());
        Address updatedAddress = addressRepository.save(address);

        UpdatedAddressResponse updatedAddressResponse =
                AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);

        return updatedAddressResponse;
    }

    @Override
    public DeletedAddressResponse delete(String id) {
        Address address = addressRepository.findById(id).get();
        addressBusinessRules.checkIfAddressDeleted(address.getDeletedDate());
        boolean isLastAddress = addressBusinessRules.cantDeleteLastAddress(id);
        address.setId(id);
        address.setDefaultAddress(isLastAddress);
        address.setDeletedDate(LocalDateTime.now());
        Address deletedAddress = addressRepository.save(address);

        DeletedAddressResponse deletedAddressResponse = AddressMapper.INSTANCE.deletedAddressResponseFromAddress(deletedAddress);
        deletedAddressResponse.setDeletedDate(deletedAddress.getDeletedDate());
        return deletedAddressResponse;
    }

    @Override
    public UpdatedDefaultAddressResponse putDefaultAddress(String id ,UpdateDefaultAddressRequest updateDefaultAddressRequest) {
        addressBusinessRules.setDefaultAddress(updateDefaultAddressRequest.getCustomerId(), id);

        Address address = addressRepository.findById(id).get();
        address.setId(id);
        address.setDefaultAddress(updateDefaultAddressRequest.isDefaultAddress());
        addressRepository.save(address);
        UpdatedDefaultAddressResponse updatedDefaultAddressResponse = new UpdatedDefaultAddressResponse(address.getId(), address.isDefaultAddress());
        return updatedDefaultAddressResponse;

    }


}
