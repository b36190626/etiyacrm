package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.RealCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.individualCustomerResponses.*;

import java.time.LocalDate;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception;
    PageInfoResponse<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, String id);
    GetIndividualCustomerResponse getById(String id);
    DeletedIndividualCustomerResponse delete(String id);

    boolean checkIfCustomerDuplicated(String nationalityIdentity);
    boolean checkIfCustomerReal(String nationalityIdentity,
                                String firstName,
                                String middleName,
                                String lastName,
                                LocalDate birthDate) throws Exception;
}
