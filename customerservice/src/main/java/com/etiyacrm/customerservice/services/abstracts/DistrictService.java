package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.*;

public interface DistrictService {

    PageInfoResponse<GetAllDistrictResponse> getAll(PageInfo pageInfo);
    GetDistrictResponse getById(String id);
    CreatedDistrictResponse add(CreateDistrictRequest createDistrictRequest);
    UpdatedDistrictResponse update(UpdateDistrictRequest updateDistrictRequest, String id);
    DeletedDistrictResponse delete(String id);
}
