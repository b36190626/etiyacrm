package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.UpdatedDistrictResponse;

public interface DistrictService {
    GetDistrictResponse getById(String id);
    CreatedDistrictResponse add(CreateDistrictRequest createDistrictRequest);
    UpdatedDistrictResponse update(UpdateDistrictRequest updateDistrictRequest, String id);
    DeletedDistrictResponse delete(String id);
}
