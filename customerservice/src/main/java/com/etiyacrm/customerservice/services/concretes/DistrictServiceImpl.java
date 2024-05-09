package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.District;
import com.etiyacrm.customerservice.repositories.DistrictRepository;
import com.etiyacrm.customerservice.services.abstracts.DistrictService;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.cityresponses.UpdatedCityResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import com.etiyacrm.customerservice.services.mappers.DistrictMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;

    @Override
    public GetDistrictResponse getById(long id) {
        District district = districtRepository.findById(id).get();
        GetDistrictResponse response = DistrictMapper.INSTANCE.getDistrictResponseFromDistrict(district);
        return response;
    }

    @Override
    public CreatedDistrictResponse add(CreateDistrictRequest createDistrictRequest) {
        District district = DistrictMapper.INSTANCE.districtFromCreateDistrictRequest(createDistrictRequest);

        City city = new City();
        city.setId(createDistrictRequest.getCityId());

        district.setCity(city);

        District createdDistrict = districtRepository.save(district);

        CreatedDistrictResponse createdDistrictResponse = DistrictMapper.INSTANCE.createdDistrictResponseFromDistrict(createdDistrict);
        return createdDistrictResponse;
    }

    @Override
    public UpdatedCityResponse update(UpdateDistrictRequest updateDistrictRequest, long id) {
        return null;
    }

    @Override
    public DeletedDistrictResponse delete(long id) {
        return null;
    }

}
