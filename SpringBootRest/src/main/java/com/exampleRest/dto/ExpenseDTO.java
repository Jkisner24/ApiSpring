package com.exampleRest.dto;

import com.exampleRest.entities.ExpenseCategory;
import com.exampleRest.entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDate expenseDate;
    private ExpenseCategory category;
    private Owner owner;
}
