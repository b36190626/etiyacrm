package com.etiyacrm.customerservice.core.exceptions.problemdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationProblemDetails extends BusinessProblemDetails {
    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("hhtp://etiya.com/excepitons/validation");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }

    private List<Map<String, String>> errors;
}
