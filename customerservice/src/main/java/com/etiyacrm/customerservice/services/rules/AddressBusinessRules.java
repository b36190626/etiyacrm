package com.etiyacrm.customerservice.services.rules;

import com.etiyacrm.customerservice.core.business.abstracts.MessageService;
import com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.repositories.AddressRepository;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.messages.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressBusinessRules {
    private AddressRepository addressRepository;
    private MessageService messageService;
    public void checkIfAddressDeleted(LocalDateTime deletedDate){
        List<Address> addresses = addressRepository.findByDeletedDate(deletedDate);
        for (Address address : addresses){
            if (address.getDeletedDate()!= null){
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.AddressDeleted));
            }
        }
    }

    public boolean checkIfOneAddress(String customerId, boolean isDefaultAddress){
        List<Address> addressList = addressRepository.findByCustomerId(customerId);
        if (addressList.size() == 1) {
            for (Address address : addressList){
                address.setDefaultAddress(true);
                return true;
            }
        }
        else {
            Address address = new Address();
            address.setDefaultAddress(isDefaultAddress);
            return false;
        }
        return isDefaultAddress;
    }

    public void cantDeleteLastAddress(String customerId){
        List<Address> addressList = addressRepository.findByCustomerId(customerId);
        if (addressList.size() == 1){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CustomerShouldHaveAtLeastOneAddress));
        }
    }


    public void setDefaultAddress(String customerId, String addressId){
        List<Address> addressList = addressRepository.findByCustomerId(customerId);

        for (Address address : addressList){
            if (address.getId().equals(addressId) || addressList.size() == 1){
                address.setDefaultAddress(true);
            }
            else {
                address.setDefaultAddress(false);
            }
            addressRepository.save(address);
        }
    }
}
