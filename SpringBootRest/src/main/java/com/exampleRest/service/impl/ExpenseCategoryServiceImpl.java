package com.exampleRest.service.impl;

import com.exampleRest.entities.Expense;
import com.exampleRest.entities.ExpenseCategory;
import com.exampleRest.persistence.ExpenseCategoryDAO;
import com.exampleRest.service.ExpenseCategoryService;
import com.exampleRest.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    @Autowired
    ExpenseCategoryDAO expenseCategoryDAO;

    @Override
    public List<ExpenseCategory> findAll() {
        return (List<ExpenseCategory>) expenseCategoryDAO.findAll();
    }

    @Override
    public Optional<ExpenseCategory> findById(Long id) {
        return expenseCategoryDAO.findById(id);
    }

    @Override
    public void save(ExpenseCategory expenseCategory) {
        expenseCategoryDAO.save(expenseCategory);
    }

    @Override
    public void deleteById(Long id) {
        expenseCategoryDAO.deleteById(id);
    }
}
