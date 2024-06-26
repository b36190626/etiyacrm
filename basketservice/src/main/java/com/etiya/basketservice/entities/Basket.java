package com.etiya.basketservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "baskets")
public class Basket {
    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "customerId")
    private String customerId;

    @Field(name = "totalPrice")
    private double totalPrice;

    private List<BasketItem> basketItems;
}
