package com.etiyacrm.customerservice.business.abstracts;

import com.etiyacrm.customerservice.business.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.business.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.business.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.etiyacrm.customerservice.business.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, Long id);
}
