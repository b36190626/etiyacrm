package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.repositories.abstracts.IndividualCustomerRepository;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.GetAllIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.mappers.IndividualCustomerMapper;
import com.etiyacrm.customerservice.services.rules.IndividualCustomerBusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;

    private IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(createIndividualCustomerRequest.getNationalityIdentity());
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(createIndividualCustomerRequest);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse = IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);

        return createdIndividualCustomerResponse;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<IndividualCustomer> response = individualCustomerRepository.findAll(pageable);

        return response.map(individualCustomer -> IndividualCustomerMapper.INSTANCE.getAllIndividualCustomerFromIndividualCustomer(individualCustomer)).getContent();
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id) {
        individualCustomerBusinessRules.nationalityIdentityCannotBeDuplicated(updateIndividualCustomerRequest.getNationalityIdentity());
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(updateIndividualCustomerRequest);
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);

        return updatedIndividualCustomerResponse;
    }
}
