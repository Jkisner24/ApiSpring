package com.exampleRest.exception;

public class ExpenseNotFound extends RuntimeException {
    public ExpenseNotFound(Long id){
        super("Expense not found with id: " + id);
    }
}

