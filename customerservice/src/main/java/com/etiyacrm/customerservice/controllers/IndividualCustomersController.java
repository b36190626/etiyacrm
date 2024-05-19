package com.etiyacrm.customerservice.controllers;

import com.etiyacrm.customerservice.core.business.paging.PageInfo;
import com.etiyacrm.customerservice.core.business.paging.PageInfoResponse;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.RealCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;

import com.etiyacrm.customerservice.services.dtos.responses.individualCustomerResponses.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("api/v1/individualcustomers")
public class IndividualCustomersController {
    private IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add")
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
        return individualCustomerService.add(createIndividualCustomerRequest);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update")
    public UpdatedIndividualCustomerResponse update(@Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest,
                                                    @PathVariable String id){
        return individualCustomerService.update(updateIndividualCustomerRequest, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    @Operation(summary = "GetAll")
    public PageInfoResponse<GetAllIndividualCustomerResponse> getAll(PageInfo pageInfo){
        return individualCustomerService.getAll(pageInfo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable String id){
        return individualCustomerService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete")
    public DeletedIndividualCustomerResponse delete(@PathVariable String id){
        return individualCustomerService.delete(id);
    }

    @GetMapping("/check-customer-real")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check Customer")
    public boolean checkIfCustomerReal(
            @RequestParam String nationalityIdentity,
            @RequestParam String firstName,
            @RequestParam(required = false) String middleName,
            @RequestParam String lastName,
            @RequestParam LocalDate birthDate
            ) throws Exception {
        return individualCustomerService.checkIfCustomerReal(
                nationalityIdentity, firstName, middleName, lastName, birthDate);
    }

    @GetMapping("/check-nationality-identity-duplicated/{nationalityIdentity}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check Nationality Identity Duplicated")
    public boolean checkIfCustomerDuplicated(@PathVariable String nationalityIdentity){
        return individualCustomerService.checkIfCustomerDuplicated(nationalityIdentity);
    }
}
