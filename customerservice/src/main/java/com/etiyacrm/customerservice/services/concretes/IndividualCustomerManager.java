package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.repositories.abstracts.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        /*individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(createIndividualCustomerRequest.getNationalityIdentityCard());

        IndividualCustomer mappedIndividualCustomer = this.modelMapperService.forRequest()
                .map(createIndividualCustomerRequest, IndividualCustomer.class);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(mappedIndividualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse = this.modelMapperService.forResponse()
                .map(createdIndividualCustomer, CreatedIndividualCustomerResponse.class);
        return createdIndividualCustomerResponse;*/
        return null;
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id) {

//        IndividualCustomer mappedIndividualCustomer = this.modelMapperService.forRequest()
//                .map(updateIndividualCustomerRequest, IndividualCustomer.class);
//        mappedIndividualCustomer.setId(id);
//        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(mappedIndividualCustomer);
//
//        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = this.modelMapperService.forResponse()
//                .map(updatedIndividualCustomer, UpdatedIndividualCustomerResponse.class);
//        return updatedIndividualCustomerResponse;
        return null;
    }
}
