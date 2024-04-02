package com.etiyacrm.customerservice.business.concretes;

import com.etiyacrm.customerservice.business.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.business.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.business.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.business.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.business.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.business.rules.IndividualCustomerBusinessRules;
import com.etiyacrm.customerservice.core.entities.utilities.mapper.ModelMapperService;
import com.etiyacrm.customerservice.dataAccess.abstracts.IndividualCustomerRepository;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private ModelMapperService modelMapperService;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(createIndividualCustomerRequest.getNationalityIdentityCard());

        IndividualCustomer mappedIndividualCustomer = this.modelMapperService.forRequest()
                .map(createIndividualCustomerRequest, IndividualCustomer.class);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(mappedIndividualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse = this.modelMapperService.forResponse()
                .map(createdIndividualCustomer, CreatedIndividualCustomerResponse.class);
        return createdIndividualCustomerResponse;
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id) {

        IndividualCustomer mappedIndividualCustomer = this.modelMapperService.forRequest()
                .map(updateIndividualCustomerRequest, IndividualCustomer.class);
        mappedIndividualCustomer.setId(id);
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(mappedIndividualCustomer);

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = this.modelMapperService.forResponse()
                .map(updatedIndividualCustomer, UpdatedIndividualCustomerResponse.class);
        return updatedIndividualCustomerResponse;
    }
}
