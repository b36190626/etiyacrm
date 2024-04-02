package com.etiyacrm.customerservice.core.exceptions.types;

public class InternalServerException extends InternalError{
    public InternalServerException(String message){
        super(message);
    }
}
