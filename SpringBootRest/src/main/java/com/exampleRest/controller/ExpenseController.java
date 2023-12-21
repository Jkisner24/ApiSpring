package com.exampleRest.controller;

import com.exampleRest.dto.ExpenseDTO;
import com.exampleRest.entities.Expense;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            List<ExpenseDTO> expenseDTOList = expenseService.findAll()
                    .stream()
                    .map(expense -> ExpenseDTO.builder()
                            .id(expense.getId())
                            .name(expense.getName())
                            .price(expense.getPrice())
                            .expenseDate(expense.getExpenseDate())
                            .category(expense.getCategory())
                            .owner(expense.getOwner())
                            .build())
                    .toList();
            return ResponseEntity.ok(expenseDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
        Optional<Expense> expenseOptional = expenseService.findById(id);

        if(expenseOptional.isPresent()){
            Expense expense = expenseOptional.get();
            ExpenseDTO expenseDTO = ExpenseDTO.builder()
                    .id(expense.getId())
                    .name(expense.getName())
                    .price(expense.getPrice())
                    .category(expense.getCategory())
                    .owner(expense.getOwner())
                    .expenseDate(expense.getExpenseDate())
                    .build();
            return ResponseEntity.ok(expenseDTO);
        }
            throw new ExpenseNotFound(id);
    } catch(Exception e){
        throw e;
    }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ExpenseDTO expenseDTO) throws URISyntaxException {
        try {
            if (expenseDTO.getName().isBlank() || expenseDTO.getPrice() == null || expenseDTO.getCategory() == null) {
                return ResponseEntity.badRequest().build();
            }
            Expense expense = Expense.builder()
                    .id(expenseDTO.getId())
                    .name(expenseDTO.getName())
                    .price(expenseDTO.getPrice())
                    .category(expenseDTO.getCategory())
                    .owner(expenseDTO.getOwner())
                    .expenseDate(expenseDTO.getExpenseDate())
                    .build();

            expenseService.save(expense);
            return ResponseEntity.created(new URI("/api/v1/expense/save"))
                    .build();
        }catch (Exception e){
            throw e;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO){
        try{
        Optional<Expense> expenseOptional = expenseService.findById(id);

        if(expenseOptional.isPresent()){
            Expense expense = expenseOptional.get();
            expense.setName(expenseDTO.getName());
            expense.setOwner(expenseDTO.getOwner());
            expense.setCategory(expenseDTO.getCategory());
            expense.setPrice(expenseDTO.getPrice());
            expense.setExpenseDate(expenseDTO.getExpenseDate());
            expenseService.save(expense);
            return ResponseEntity.ok("Register updated");
        }
            throw new ExpenseNotFound(id);
    }catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            if(id != null){
                expenseService.deleteById(id);
                return ResponseEntity.ok("Register deleted");
            }
                throw new ExpenseNotFound(null);
        }catch (Exception e){
            throw e;
        }
    }

}
