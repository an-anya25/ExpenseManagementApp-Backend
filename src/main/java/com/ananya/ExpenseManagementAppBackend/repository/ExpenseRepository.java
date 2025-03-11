package com.ananya.ExpenseManagementAppBackend.repository;

import com.ananya.ExpenseManagementAppBackend.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
}
