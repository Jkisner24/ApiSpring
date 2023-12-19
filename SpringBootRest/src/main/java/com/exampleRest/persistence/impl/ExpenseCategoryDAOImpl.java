package com.exampleRest.persistence.impl;

import com.exampleRest.entities.ExpenseCategory;
import com.exampleRest.persistence.ExpenseCategoryDAO;
import com.exampleRest.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ExpenseCategoryDAOImpl implements ExpenseCategoryDAO {
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;
    @Override
    public List<ExpenseCategory> findAll() {
        return (List<ExpenseCategory>) expenseCategoryRepository.findAll();
    }

    @Override
    public Optional<ExpenseCategory> findById(Long id) {
        return expenseCategoryRepository.findById(id);
    }

    @Override
    public void save(ExpenseCategory expenseCategory) {
        expenseCategoryRepository.save(expenseCategory);
    }

    @Override
    public void deleteById(Long id) {
        expenseCategoryRepository.deleteById(id);
    }
}
