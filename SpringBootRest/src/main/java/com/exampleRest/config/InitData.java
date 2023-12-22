package com.exampleRest.config;

import com.exampleRest.entities.Expense;
import com.exampleRest.entities.ExpenseCategory;
import com.exampleRest.entities.Owner;
import com.exampleRest.service.ExpenseCategoryService;
import com.exampleRest.service.ExpenseService;
import com.exampleRest.service.OwnerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class InitData {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Autowired
    private OwnerService ownerService;

    @PostConstruct
    public void uploadInitialData() {
        if (expenseService.findAll().isEmpty() && expenseCategoryService.findAll().isEmpty() && ownerService.findAll().isEmpty()) {
            ExpenseCategory category1 = ExpenseCategory.builder()
                    .name("Foods")
                    .build();

            ExpenseCategory category2 = ExpenseCategory.builder()
                    .name("Transport")
                    .build();

            expenseCategoryService.save(category1);
            expenseCategoryService.save(category2);

            Owner owner1 = Owner.builder()
                    .name("Juan")
                    .build();

            Owner owner2 = Owner.builder()
                    .name("Maria")
                    .build();

            ownerService.save(owner1);
            ownerService.save(owner2);

            Expense expense1 = Expense.builder()
                    .expenseDate(LocalDate.now())
                    .name("Food 1")
                    .price(BigDecimal.valueOf(20.0))
                    .category(category1)
                    .owner(owner1)
                    .build();

            Expense expense2 = Expense.builder()
                    .expenseDate(LocalDate.now())
                    .name("Public transport")
                    .price(BigDecimal.valueOf(15.5))
                    .category(category2)
                    .owner(owner2)
                    .build();

            expenseService.save(expense1);
            expenseService.save(expense2);
        }
    }
}