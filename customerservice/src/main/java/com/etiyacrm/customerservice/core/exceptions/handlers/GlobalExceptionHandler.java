package com.etiyacrm.customerservice.core.exceptions.handlers;

import com.etiyacrm.customerservice.core.exceptions.problemdetails.BusinessProblemDetails;
import com.etiyacrm.customerservice.core.exceptions.problemdetails.InternalServerProblemDetails;
import com.etiyacrm.customerservice.core.exceptions.problemdetails.ValidationProblemDetails;
import com.etiyacrm.customerservice.core.exceptions.types.BusinessException;
import com.etiyacrm.customerservice.core.exceptions.types.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails hendleBusinessException(BusinessException businessException){
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(businessException.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception){
        List<Map<String, String>> errorList =
                exception.getBindingResult().getFieldErrors().stream().map(
                        fieldError -> {
                            Map<String, String> validationError = new HashMap<>();
                            validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
                            return validationError;
                        }).collect(Collectors.toList());
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(errorList);
        return validationProblemDetails;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerProblemDetails handleInternalServerException(InternalServerException internalServerException){
        InternalServerProblemDetails internalServerProblemDetails = new InternalServerProblemDetails();
        internalServerProblemDetails.setDetail(internalServerException.getMessage());
        return internalServerProblemDetails;
    }


}
