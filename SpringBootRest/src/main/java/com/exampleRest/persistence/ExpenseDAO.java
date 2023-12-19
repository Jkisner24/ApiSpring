package com.exampleRest.persistence;

import com.exampleRest.entities.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseDAO {
    List<Expense> findAll();
    Optional<Expense> findById(Long id);
    void save(Expense expense);
    void deleteById(Long id);

}
