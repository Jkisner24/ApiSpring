package com.exampleRest.controller;

import com.exampleRest.dto.ExpenseCategoryDTO;
import com.exampleRest.dto.ExpenseDTO;
import com.exampleRest.entities.Expense;
import com.exampleRest.entities.ExpenseCategory;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/expenseCategory")
public class ExpenseCategoryController {
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            List<ExpenseCategoryDTO> expenseCategoryDTOList = expenseCategoryService.findAll()
                    .stream()
                    .map(expenseCategory -> ExpenseCategoryDTO.builder()
                            .id(expenseCategory.getId())
                            .name(expenseCategory.getName())
                            .expenses(expenseCategory.getExpenses())
                            .build())
                    .toList();
            return ResponseEntity.ok(expenseCategoryDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Optional<ExpenseCategory> expenseCategoryOptional = expenseCategoryService.findById(id);

            if (expenseCategoryOptional.isPresent()) {
                ExpenseCategory expenseCategory = expenseCategoryOptional.get();
                ExpenseCategoryDTO expenseCategoryDTO = ExpenseCategoryDTO.builder()
                        .id(expenseCategory.getId())
                        .name(expenseCategory.getName())
                        .expenses(expenseCategory.getExpenses())
                        .build();
            return ResponseEntity.ok(expenseCategoryDTO);
        }
            throw new ExpenseNotFound(id);

        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ExpenseCategoryDTO expenseCategoryDTO) throws URISyntaxException {
        try{
            if (expenseCategoryDTO.getName().isBlank()) {
                return ResponseEntity.badRequest().build();
            }
            ExpenseCategory expenseCategory = ExpenseCategory.builder()
                .id(expenseCategoryDTO.getId())
                .name(expenseCategoryDTO.getName())
                .expenses(expenseCategoryDTO.getExpenses())
                .build();
            expenseCategoryService.save(expenseCategory);
            return ResponseEntity.created(new URI("/api/v1/expenseCategory/save"))
                .build();
        }catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExpenseCategoryDTO expenseCategoryDTO){
        try{
            Optional<ExpenseCategory> expenseCategoryOptional = expenseCategoryService.findById(id);

            if(expenseCategoryOptional.isPresent()){
                ExpenseCategory expenseCategory = expenseCategoryOptional.get();
                expenseCategory.setName(expenseCategoryDTO.getName());
                expenseCategory.setExpenses(expenseCategoryDTO.getExpenses());
                expenseCategoryService.save(expenseCategory);
                return ResponseEntity.ok("Register updated");
            }
            throw new ExpenseNotFound(id);
    }catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            if (id != null) {
                expenseCategoryService.deleteById(id);
                return ResponseEntity.ok("Register updated");
            }
            throw new ExpenseNotFound(null);
        }catch (Exception e){
            throw e;
        }
    }
}