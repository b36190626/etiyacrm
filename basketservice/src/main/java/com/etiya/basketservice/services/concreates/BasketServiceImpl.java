package com.etiya.basketservice.services.concreates;

import com.etiya.basketservice.clients.CatalogServiceClient;
import com.etiya.basketservice.entities.Basket;
import com.etiya.basketservice.entities.BasketItem;
import com.etiya.basketservice.repositories.BasketRepository;
import com.etiya.basketservice.services.abstracts.BasketService;
import com.etiya.basketservice.services.dtos.responses.GetCatalogProductOfferResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private BasketRepository basketRepository;
    private CatalogServiceClient catalogServiceClient;
    @Override
    public void add(String customerId, String productOfferId) {
        Basket basket = new Basket();
        GetCatalogProductOfferResponse getCatalogProductOfferResponse =
                catalogServiceClient.getById(productOfferId);
        List<BasketItem> basketItemList = new ArrayList<>();
        for (BasketItem basketItem : basketItemList){
            basketItem.setProductOfferId(productOfferId);
            basketItem.setPrice(getCatalogProductOfferResponse.getProductOfferPrice());
            basketItem.setProductOfferName(getCatalogProductOfferResponse.getProductOfferName());
            basket.setCustomerId(customerId); // basket.getCustomerId
            basket.setTotalPrice(basket.getTotalPrice() + basketItem.getPrice());
        }
        basket.setBasketItems(basketItemList);
        basketRepository.save(basket);
    }
}
