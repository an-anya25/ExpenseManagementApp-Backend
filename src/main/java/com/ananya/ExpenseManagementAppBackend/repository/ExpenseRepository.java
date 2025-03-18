package com.ananya.ExpenseManagementAppBackend.repository;

import com.ananya.ExpenseManagementAppBackend.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for Expense resource
 *
 * @author Ananya
 */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    /**
     * It will find a single expense from the database
     *
     * @param expenseId
     * @return Optional
     */
    Optional<ExpenseEntity> findByExpenseId(String expenseId);
}
