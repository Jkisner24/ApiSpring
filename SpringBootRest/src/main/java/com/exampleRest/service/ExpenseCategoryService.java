package com.exampleRest.service;

import com.exampleRest.entities.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseCategoryService {
    List<ExpenseCategory> findAll();
    Optional<ExpenseCategory> findById(Long id);
    void save(ExpenseCategory expenseCategory);
    void deleteById(Long id);
}


