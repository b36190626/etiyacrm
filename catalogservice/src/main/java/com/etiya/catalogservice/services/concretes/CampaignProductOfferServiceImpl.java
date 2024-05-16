package com.etiya.catalogservice.services.concretes;

import com.etiya.catalogservice.entities.CampaignProductOffer;
import com.etiya.catalogservice.repositories.CampaignProductOfferRepository;
import com.etiya.catalogservice.services.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.services.dtos.requests.campaignProductOfferRequests.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.campaignProductOfferRequests.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.campaignProductOfferResponses.*;
import com.etiya.catalogservice.services.mappers.CampaignProductOfferMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CampaignProductOfferServiceImpl implements CampaignProductOfferService {
    private CampaignProductOfferRepository campaignProductOfferRepository;
    @Override
    public CreatedCampaignProductOfferResponse add(CreateCampaignProductOfferRequest createCampaignProductOfferRequest) {
        CampaignProductOffer campaignProductOffer =
                CampaignProductOfferMapper.INSTANCE.campaignProductOfferFromCreateCampaignProductOfferRequest(createCampaignProductOfferRequest);
        CampaignProductOffer createdCampaignProductOffer = campaignProductOfferRepository.save(campaignProductOffer);

        CreatedCampaignProductOfferResponse createdCampaignProductOfferResponse =
                CampaignProductOfferMapper.INSTANCE.createdCampaignProductOfferResponseFromCampaignProductOffer(createdCampaignProductOffer);

        return createdCampaignProductOfferResponse;
    }

    @Override
    public UpdatedCampaignProductOfferResponse update(UpdateCampaignProductOfferRequest updateCampaignProductOfferRequest, String id) {
        CampaignProductOffer campaignProductOffer =
                CampaignProductOfferMapper.INSTANCE.campaignProductOfferFromUpdateCampaignProductOfferRequest(updateCampaignProductOfferRequest);
        campaignProductOffer.setId(id);
        campaignProductOffer.setUpdatedDate(LocalDateTime.now());
        CampaignProductOffer updatedCampaignProductOffer = campaignProductOfferRepository.save(campaignProductOffer);

        UpdatedCampaignProductOfferResponse updatedCampaignProductOfferResponse =
                CampaignProductOfferMapper.INSTANCE.updatedCampaignProductOfferResponseFromCampaignProductOffer(updatedCampaignProductOffer);

        return updatedCampaignProductOfferResponse;
    }

    @Override
    public List<GetAllCampaignProductOfferResponse> getAll() {
        List<CampaignProductOffer> campaignProductOffers = campaignProductOfferRepository.findAll();
        List<GetAllCampaignProductOfferResponse> getAllCampaignProductOfferResponses = campaignProductOffers.stream().map(CampaignProductOfferMapper.INSTANCE::getAllCampaignProductOfferResponseFromCampaignProductOffer)
                .collect(Collectors.toList());
        return getAllCampaignProductOfferResponses;
    }

    @Override
    public GetCampaignProductOfferResponse getById(String id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id).get();

        GetCampaignProductOfferResponse getCampaignProductOfferResponse =
                CampaignProductOfferMapper.INSTANCE.getCampaignProductOfferResponseFromCampaignProductOffer(campaignProductOffer);
        return getCampaignProductOfferResponse;
    }

    @Override
    public DeletedCampaignProductOfferResponse delete(String id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id).get();
        campaignProductOffer.setId(id);
        campaignProductOffer.setDeletedDate(LocalDateTime.now());
        CampaignProductOffer deletedCampaignProductOffer = campaignProductOfferRepository.save(campaignProductOffer);

        DeletedCampaignProductOfferResponse deletedCampaignProductOfferResponse =
                CampaignProductOfferMapper.INSTANCE.deletedCampaignProductOfferResponseFromCampaignProductOffer(deletedCampaignProductOffer);

        return deletedCampaignProductOfferResponse;
    }
}
