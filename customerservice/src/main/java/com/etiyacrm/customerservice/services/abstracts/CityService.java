package com.etiyacrm.customerservice.services.abstracts;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cityresponses.*;

import java.util.List;

public interface CityService {
    List<GetAllCityResponse> getAll(PageInfo pageInfo);

    GetCityResponse getById(long id);
    CreatedCityResponse add(CreateCityRequest createCityRequest);
    UpdatedCityResponse update(UpdateCityRequest updateCityRequest, long id);
    DeletedCityResponse delete(long id);
}
