package com.ananya.ExpenseManagementAppBackend.service;

import com.ananya.ExpenseManagementAppBackend.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 *
 * @author Ananya
 */
public interface ExpenseService {

    /**
     * It will fetch all the expenses from the database
     *
     * @return list
     */
    List<ExpenseDTO> getAllExpenses();

    /**
     * It will fetch a single expense details from the database
     *
     * @param expenseId
     * @return ExpenseDTO
     */
    ExpenseDTO getExpenseByExpenseId(String expenseId);
}

