package com.etiya.searchservice.controllers;

import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.abstracts.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("api/v1/search-service")
public class SearchCustomerController {
    private FilterService filterService;

    @GetMapping("/{nationalityIdentity}")
    public Customer searchByNationalityIdentity(@PathVariable String nationalityIdentity){
        return filterService.searchByNationalityIdentity(nationalityIdentity);
    }
}
