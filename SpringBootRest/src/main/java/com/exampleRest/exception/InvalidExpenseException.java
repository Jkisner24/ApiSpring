package com.exampleRest.exception;

public class InvalidExpenseException extends RuntimeException{
    public InvalidExpenseException(String message){
        super(message);
    }
}
