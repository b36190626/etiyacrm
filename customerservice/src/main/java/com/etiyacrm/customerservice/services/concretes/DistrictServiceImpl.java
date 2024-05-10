package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.entities.City;
import com.etiyacrm.customerservice.entities.District;
import com.etiyacrm.customerservice.repositories.DistrictRepository;
import com.etiyacrm.customerservice.services.abstracts.DistrictService;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.UpdatedDistrictResponse;
import com.etiyacrm.customerservice.services.mappers.DistrictMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;

    @Override
    public GetDistrictResponse getById(String id) {
        District district = districtRepository.findById(id).get();
        System.out.println(id);
        GetDistrictResponse response = DistrictMapper.INSTANCE.getDistrictResponseFromDistrict(district);
        response.setCityId(district.getCity().getId());
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
        createdDistrictResponse.setCityId(city.getId());
        return createdDistrictResponse;
    }

    @Override
    public UpdatedDistrictResponse update(UpdateDistrictRequest updateDistrictRequest, String id) {
        //rule eklenecek
        City city = new City();
        city.setId(updateDistrictRequest.getCityId());

        District district =DistrictMapper.INSTANCE.districtFromUpdateDistrictRequest(updateDistrictRequest);
        district.setId(id);
        district.setCity(city);
        district.setUpdatedDate(LocalDateTime.now());
        District updatedDistrict = districtRepository.save(district);

        UpdatedDistrictResponse updatedDistrictResponse = DistrictMapper.INSTANCE.updatedDistrictResponseFromDistrict(updatedDistrict);
        return updatedDistrictResponse;
    }

    @Override
    public DeletedDistrictResponse delete(String id) {
        //rule eklenecek

        District district = districtRepository.findById(id).get();
        district.setId(id);
        district.setDeletedDate(LocalDateTime.now());
        District deletedDistrict = districtRepository.save(district);

        DeletedDistrictResponse deletedDistrictResponse = DistrictMapper.INSTANCE.deletedDistrictResponseFromDistrict(deletedDistrict);
        deletedDistrictResponse.setDeletedDate(deletedDistrict.getDeletedDate());
        return deletedDistrictResponse;
    }

}
