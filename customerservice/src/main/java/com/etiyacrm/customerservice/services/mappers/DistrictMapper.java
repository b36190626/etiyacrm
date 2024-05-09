package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.Address;
import com.etiyacrm.customerservice.entities.District;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.CreatedAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.DeletedAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.GetAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.addressResponses.UpdatedAddressResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.UpdatedDistrictResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DistrictMapper {

    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    District districtFromCreateDistrictRequest(CreateDistrictRequest createDistrictRequest);
    CreatedDistrictResponse createdDistrictResponseFromDistrict(District district);
    District districtFromUpdateDistrictRequest(UpdateDistrictRequest updateDistrictRequest);
    UpdatedDistrictResponse updatedDistrictResponseFromDistrict(District district);
    DeletedDistrictResponse deletedDistrictResponseFromDistrict(District district);
    GetDistrictResponse getDistrictResponseFromDistrict(District district);
}
