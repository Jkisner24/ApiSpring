package com.exampleRest.service.impl;

import com.exampleRest.entities.Expense;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.persistence.ExpenseDAO;
import com.exampleRest.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseDAO expenseDAO;

    @Override
    public List<Expense> findAll() {
        return (List<Expense>) expenseDAO.findAll();
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return Optional.ofNullable(expenseDAO.findById(id)
                .orElseThrow(() -> new ExpenseNotFound(id)));
    }

    @Override
    public void save(Expense expense) {
        expenseDAO.save(expense);
    }

    @Override
    public void deleteById(Long id) {
        expenseDAO.deleteById(id);
    }
}
