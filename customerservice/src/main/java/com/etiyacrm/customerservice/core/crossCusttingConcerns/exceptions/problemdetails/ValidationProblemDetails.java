package com.etiyacrm.customerservice.core.crossCusttingConcerns.exceptions.problemdetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationProblemDetails extends BusinessProblemDetails {

    private Map<String, String> errors;

    public ValidationProblemDetails() {
        setTitle("Validation Exception");
        setType("https://etiya.com/exceptions/validation");
        setStatus("400");
        setDetail("Validation Rule Problems");
    }
}
