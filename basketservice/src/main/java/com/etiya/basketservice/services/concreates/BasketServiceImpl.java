package com.etiya.basketservice.services.concreates;

import com.etiya.basketservice.clients.CatalogServiceClient;
import com.etiya.basketservice.clients.CustomerServiceClient;
import com.etiya.basketservice.entities.Basket;
import com.etiya.basketservice.entities.BasketItem;
import com.etiya.basketservice.repositories.BasketRepository;
import com.etiya.basketservice.services.abstracts.BasketService;
import com.etiya.basketservice.services.dtos.responses.GetCatalogProductOfferResponse;
import com.etiya.basketservice.services.dtos.responses.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private BasketRepository basketRepository;
    private CatalogServiceClient catalogServiceClient;
    private CustomerServiceClient customerServiceClient;
    @Override
    public void add(String customerId, String productOfferId) {
        GetCatalogProductOfferResponse getCatalogProductOfferResponse =
                catalogServiceClient.getById(productOfferId);
        GetCustomerResponse getCustomerResponse = customerServiceClient.getById(customerId);

        Basket basket = new Basket();
        List<BasketItem> basketItemList = new ArrayList<>();

        BasketItem basketItem = new BasketItem();
        basketItem.setCampaignId(getCatalogProductOfferResponse.getCatalogId());
        basketItem.setProductOfferId(getCatalogProductOfferResponse.getProductOfferId());
        basketItem.setPrice(getCatalogProductOfferResponse.getProductOfferTotalPrice());
        basketItem.setProductOfferName(getCatalogProductOfferResponse.getProductOfferName());
        basketItem.setId(UUID.randomUUID().toString());
        basketItemList.add(basketItem);

        Iterator<BasketItem> iterator = basketItemList.iterator();
        double totalPrice = 0;

        while (iterator.hasNext()) {
            BasketItem item = iterator.next();
            totalPrice += item.getPrice();
        }

        basket.setCustomerId(getCustomerResponse.getId());
        basket.setTotalPrice(totalPrice);
        basket.setId(UUID.randomUUID().toString());
        basket.setBasketItems(basketItemList);

        System.out.println("Basket Items: " + basketItemList);
        System.out.println("Basket: " + basket);

        basketRepository.save(basket);
    }
}
