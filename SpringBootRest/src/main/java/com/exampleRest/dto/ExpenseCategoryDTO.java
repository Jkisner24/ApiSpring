package com.exampleRest.dto;

import com.exampleRest.entities.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryDTO {
    private Long id;
    private String name;
    private List<Expense> expenses;
}
