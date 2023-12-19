package com.exampleRest.service;

import com.exampleRest.entities.Expense;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.exception.InvalidExpenseException;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> findAll();
    Optional<Expense> findById(Long id) throws ExpenseNotFound;
    void save(Expense expense) throws InvalidExpenseException;
    void deleteById(Long id) throws InvalidExpenseException;

}
