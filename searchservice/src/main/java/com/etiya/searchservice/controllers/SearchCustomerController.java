package com.etiya.searchservice.controllers;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.abstracts.FilterService;
import com.etiya.searchservice.service.dtos.requests.SearchRequest;
import com.etiya.searchservice.service.dtos.responses.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("api/v1/search-service")
public class SearchCustomerController {
    private FilterService filterService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> search(@RequestBody SearchRequest searchRequest){
        return this.filterService.search(searchRequest);
    }

}
