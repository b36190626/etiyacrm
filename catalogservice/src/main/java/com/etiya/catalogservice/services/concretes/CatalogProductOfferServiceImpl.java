package com.etiya.catalogservice.services.concretes;

import com.etiya.catalogservice.entities.CatalogProductOffer;
import com.etiya.catalogservice.repositories.CatalogProductOfferRepository;
import com.etiya.catalogservice.services.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.services.dtos.requests.catalogProductOfferRequests.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.services.dtos.requests.catalogProductOfferRequests.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.catalogProductOfferResponses.*;
import com.etiya.catalogservice.services.mappers.CatalogProductOfferMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogProductOfferServiceImpl implements CatalogProductOfferService {
    private CatalogProductOfferRepository catalogProductOfferRepository;
    @Override
    public CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest createCatalogProductOfferRequest) {
        CatalogProductOffer catalogProductOffer =
                CatalogProductOfferMapper.INSTANCE.catalogProductOfferFromCreateCatalogProductOfferRequest(createCatalogProductOfferRequest);

        CatalogProductOffer createdCatalogProductOffer = catalogProductOfferRepository.save(catalogProductOffer);

        CreatedCatalogProductOfferResponse createdCatalogProductOfferResponse =
                CatalogProductOfferMapper.INSTANCE.createdCatalogProductOfferResponseFromCatalogProductOffer(createdCatalogProductOffer);

        return createdCatalogProductOfferResponse;
    }

    @Override
    public UpdatedCatalogProductOfferResponse update(UpdateCatalogProductOfferRequest updateCatalogProductOfferRequest, String id) {
        CatalogProductOffer catalogProductOffer =
                CatalogProductOfferMapper.INSTANCE.catalogProductOfferFromUpdateCatalogProductOfferRequest(updateCatalogProductOfferRequest);
        catalogProductOffer.setId(id);
        catalogProductOffer.setUpdatedDate(LocalDateTime.now());
        CatalogProductOffer updatedCatalogProductOffer = catalogProductOfferRepository.save(catalogProductOffer);

        UpdatedCatalogProductOfferResponse updatedCatalogProductOfferResponse =
                CatalogProductOfferMapper.INSTANCE.updatedCatalogProductOfferResponseFromCatalogProductOffer(updatedCatalogProductOffer);

        return updatedCatalogProductOfferResponse;
    }

    @Override
    public List<GetAllCatalogProductOfferResponse> getAll() {
        List<CatalogProductOffer> catalogProductOffers = catalogProductOfferRepository.findAll();

        List<GetAllCatalogProductOfferResponse> getAllCatalogProductOfferResponses = catalogProductOffers.stream()
                .map(CatalogProductOfferMapper.INSTANCE::getAllCatalogProductOfferResponseFromCatalogProductOffer)
                .collect(Collectors.toList());

        return getAllCatalogProductOfferResponses;
    }

    @Override
    public GetCatalogProductOfferResponse findByProductOfferId(String productOfferId) {
        CatalogProductOffer catalogProductOffer = catalogProductOfferRepository.findByProductOfferId(productOfferId);

        GetCatalogProductOfferResponse getCatalogProductOfferResponse =
                CatalogProductOfferMapper.INSTANCE.getCatalogProductOfferResponseFromCatalogProductOffer(catalogProductOffer);
        return getCatalogProductOfferResponse;
    }

    @Override
    public List<GetCatalogProductOfferResponse> findAllByProductOfferId(String id) {
        List<CatalogProductOffer> catalogProductOfferList = catalogProductOfferRepository.findAllByProductOfferId(id);

        List<GetCatalogProductOfferResponse> getCatalogProductOfferResponses = catalogProductOfferList.stream()
                .map(CatalogProductOfferMapper.INSTANCE::getCatalogProductOfferResponseFromCatalogProductOffer)
                .collect(Collectors.toList());
        return getCatalogProductOfferResponses;
    }

    @Override
    public GetCatalogProductOfferResponse findByCatalogId(String catalogId) {
        CatalogProductOffer catalogProductOffer = catalogProductOfferRepository.findByCatalogId(catalogId);
        GetCatalogProductOfferResponse getCatalogProductOfferResponse =
                CatalogProductOfferMapper.INSTANCE.getCatalogProductOfferResponseFromCatalogProductOffer(catalogProductOffer);
        return getCatalogProductOfferResponse;
    }

    @Override
    public List<GetCatalogProductOfferResponse> findAllByCatalogId(String catalogId) {
        List<CatalogProductOffer> catalogProductOfferList = catalogProductOfferRepository.findAllByCatalogId(catalogId);

        List<GetCatalogProductOfferResponse> getCatalogProductOfferResponseList =
                catalogProductOfferList.stream().map(CatalogProductOfferMapper.INSTANCE::getCatalogProductOfferResponseFromCatalogProductOffer)
                        .collect(Collectors.toList());
        return getCatalogProductOfferResponseList;
    }

    @Override
    public DeletedCatalogProductOfferResponse delete(String id) {
        CatalogProductOffer catalogProductOffer = catalogProductOfferRepository.findById(id).get();
        catalogProductOffer.setId(id);
        catalogProductOffer.setDeletedDate(LocalDateTime.now());
        CatalogProductOffer deletedCatalogProductOffer = catalogProductOfferRepository.save(catalogProductOffer);

        DeletedCatalogProductOfferResponse deletedCatalogProductOfferResponse =
                CatalogProductOfferMapper.INSTANCE.deletedCatalogProductOfferResponseFromCatalogProductOffer(deletedCatalogProductOffer);

        return deletedCatalogProductOfferResponse;
    }
}
