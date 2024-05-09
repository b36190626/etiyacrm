package com.etiyacrm.customerservice.controllers;

import com.etiyacrm.customerservice.services.abstracts.DistrictService;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.CreatedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.DeletedDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.GetDistrictResponse;
import com.etiyacrm.customerservice.services.dtos.responses.districtResponses.UpdatedDistrictResponse;
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update")
    public UpdatedDistrictResponse update(@Valid @RequestBody UpdateDistrictRequest updateDistrictRequest,
                                          @PathVariable String id){
        return districtService.update(updateDistrictRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete")
    public DeletedDistrictResponse delete(@PathVariable String id){
        return districtService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GetById")
    public GetDistrictResponse getById(@PathVariable String id){
        return districtService.getById(id);
    }

}
