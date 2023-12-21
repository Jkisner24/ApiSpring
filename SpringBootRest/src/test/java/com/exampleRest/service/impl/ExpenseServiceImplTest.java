package com.exampleRest.service.impl;

import com.exampleRest.entities.Expense;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.persistence.ExpenseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExpenseServiceImplTest {

    @Mock
    private ExpenseDAO expenseDAO;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Expense> expectedExpenses = Arrays.asList(new Expense(), new Expense());
        when(expenseDAO.findAll()).thenReturn(expectedExpenses);

        // Act
        List<Expense> result = expenseService.findAll();

        // Assert
        assertEquals(expectedExpenses, result);
    }

    @Test
    void testFindById_ExpenseFound() {
        // Arrange
        Long expenseId = 1L;
        Expense expectedExpense = new Expense();
        when(expenseDAO.findById(expenseId)).thenReturn(Optional.of(expectedExpense));

        // Act
        Optional<Expense> result = expenseService.findById(expenseId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedExpense, result.get());
    }

    @Test
    void testFindById_ExpenseNotFound() {
        // Arrange
        Long expenseId = 1L;
        when(expenseDAO.findById(expenseId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ExpenseNotFound.class, () -> expenseService.findById(expenseId));
    }

    @Test
    void testSave() {
        // Arrange
        Expense expenseToSave = new Expense();

        // Act
        expenseService.save(expenseToSave);

        // Assert
        verify(expenseDAO, times(1)).save(expenseToSave);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long expenseId = 1L;

        // Act
        expenseService.deleteById(expenseId);

        // Assert
        verify(expenseDAO, times(1)).deleteById(expenseId);
    }

}
