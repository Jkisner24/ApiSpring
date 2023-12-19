package com.exampleRest.persistence.impl;

import com.exampleRest.entities.Expense;
import com.exampleRest.persistence.ExpenseDAO;
import com.exampleRest.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ExpenseDAOImpl implements ExpenseDAO {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public List<Expense> findAll() {
        return (List<Expense>) expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }
}
