package com.etiyacrm.customerservice.controllers;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.services.abstracts.CityService;
import com.etiyacrm.customerservice.services.dtos.requests.CityRequests.CreateCityRequest;
import com.etiyacrm.customerservice.services.dtos.responses.CityResponses.CreatedCityResponse;
import com.etiyacrm.customerservice.services.dtos.responses.CityResponses.GetAllCityResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
@AllArgsConstructor
public class CitiesController {
    private CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add")
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest createCityRequest){
        return cityService.add(createCityRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GetAll")
    public List<GetAllCityResponse> getAll(PageInfo pageInfo){
        return cityService.getAll(pageInfo);
    }
}
