package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cityresponses.*;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;

public interface DistrictService {
    GetDistrictResponse getById(long id);
    CreatedDistrictResponse add(CreateDistrictRequest createDistrictRequest);
    UpdatedCityResponse update(UpdateDistrictRequest updateDistrictRequest, long id);
    DeletedDistrictResponse delete(long id);
}
