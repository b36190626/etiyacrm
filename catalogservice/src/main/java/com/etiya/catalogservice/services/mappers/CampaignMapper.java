package com.etiya.catalogservice.services.mappers;

import com.etiya.catalogservice.entities.Campaign;
import com.etiya.catalogservice.services.dtos.requests.campaignRequests.CreateCampaignRequest;
import com.etiya.catalogservice.services.dtos.requests.campaignRequests.UpdateCampaignRequest;
import com.etiya.catalogservice.services.dtos.responses.campaignResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    //@Mapping(source = "customer.id", target = "customerId")
    GetAllCampaignResponse getAllCampaignResponseFromCampaign(Campaign campaign);

    //@Mapping(source = "customerId", target = "customer.id")
    //@Mapping(source = "districtId", target = "district.id")
    Campaign campaignFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest);
    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    CreatedCampaignResponse createdCampaignResponseFromCampaign(Campaign campaign);
    Campaign campaignFromUpdateCampaignRequest(UpdateCampaignRequest updateCampaignRequest);
    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    UpdatedCapmaignResponse updatedCampaignResponseFromCampaign(Campaign campaign);
    DeletedCampaignResponse deletedCampaignResponseFromCampaign(Campaign campaign);

    //@Mapping(source = "customer.id", target = "customerId")
    //@Mapping(source = "district.id", target = "districtId")
    GetCampaignResponse getCampaignResponseFromCampaign(Campaign campaign);

}
