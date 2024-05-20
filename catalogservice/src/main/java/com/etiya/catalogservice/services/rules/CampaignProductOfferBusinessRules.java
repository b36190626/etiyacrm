package com.etiya.catalogservice.services.rules;

import com.etiya.catalogservice.entities.Campaign;
import com.etiya.catalogservice.entities.CampaignProductOffer;
import com.etiya.catalogservice.entities.Product;
import com.etiya.catalogservice.entities.ProductOffer;
import com.etiya.catalogservice.repositories.ProductRepository;
import com.etiya.catalogservice.services.abstracts.CampaignService;
import com.etiya.catalogservice.services.abstracts.ProductOfferService;
import com.etiya.catalogservice.services.abstracts.ProductService;
import com.etiya.catalogservice.services.dtos.requests.campaignProductOfferRequests.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.services.dtos.responses.campaignResponses.GetCampaignResponse;
import com.etiya.catalogservice.services.dtos.responses.productOfferResponses.GetProductOfferResponse;
import com.etiya.catalogservice.services.dtos.responses.productResponses.GetProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CampaignProductOfferBusinessRules {
    private CampaignService campaignService;
    private ProductOfferService productOfferService;

    public ProductOffer calculateDiscountedPrice(CreateCampaignProductOfferRequest createCampaignProductOfferRequest){
        GetCampaignResponse campaign = campaignService.getById(createCampaignProductOfferRequest.getCampaignId());
        GetProductOfferResponse productOfferPrice = productOfferService.getById(createCampaignProductOfferRequest.getProductOfferId());

        double productPrice = productOfferPrice.getTotalPrice();
        double discount = productPrice * campaign.getDiscount() / 100;
        double totalPrice = productPrice - discount;

        ProductOffer productOffer = new ProductOffer();
        productOffer.setTotalPrice(totalPrice);
        productOffer.setName(productOfferPrice.getName());
        productOffer.setDescription(productOfferPrice.getDescription());
        return productOffer;
    }
}
