package com.etiya.catalogservice.services.concretes;

import com.etiya.catalogservice.entities.Product;
import com.etiya.catalogservice.repositories.ProductRepository;
import com.etiya.catalogservice.services.abstracts.ProductService;
import com.etiya.catalogservice.services.dtos.responses.productResponses.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public CreatedProductResponse add() {
        Product product = new Product();
        productRepository.save(product);

        CreatedProductResponse createdProductResponse = new CreatedProductResponse();
        createdProductResponse.setId(product.getId());

        return createdProductResponse;
    }

}
