package com.etiyacrm.customerservice.controllers;

import com.etiyacrm.customerservice.services.abstracts.DistrictService;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("customerservice/api/v1/districts")
public class DistrictController {

    private DistrictService districtService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add")
    public CreatedDistrictResponse add(@Valid @RequestBody CreateDistrictRequest createDistrictRequest){
        return districtService.add(createDistrictRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GetById")
    public GetDistrictResponse getById(@PathVariable long id){
        return districtService.getById(id);
    }

}
