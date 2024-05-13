package com.etiya.searchservice.controllers;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.abstracts.FilterService;
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
    public List<SearchResponse> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String mobilePhone,
            @RequestParam(required = false) String nationalityIdentity,
            @RequestParam(required = false) String orderNumber,
            @RequestParam(required = false) String accountNumber
    ) {
        return this.filterService.search(
                id, firstName, lastName, mobilePhone, nationalityIdentity, orderNumber, accountNumber
        );
    }

}
