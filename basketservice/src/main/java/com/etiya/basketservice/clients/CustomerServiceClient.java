package com.etiya.basketservice.clients;

import com.etiya.basketservice.services.dtos.responses.GetCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url ="http://localhost:8001/",value = "customerservice")
public interface CustomerServiceClient {
    @RequestMapping(method = RequestMethod.GET,value = "customerservice/api/v1/individualcustomers/{id}")
    GetCustomerResponse getById(@PathVariable String id);
}
