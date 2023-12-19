package com.exampleRest.persistence;

import com.exampleRest.entities.Expense;
import com.exampleRest.entities.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseCategoryDAO {
    List<ExpenseCategory> findAll();
    Optional<ExpenseCategory> findById(Long id);
    void save(ExpenseCategory expenseCategory);
    void deleteById(Long id);
}
