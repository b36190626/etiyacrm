package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.GetAllIndividualCustomerResponse;
import com.etiyacrm.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    List<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id);
}