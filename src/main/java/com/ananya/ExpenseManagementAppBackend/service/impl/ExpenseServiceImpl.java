package com.ananya.ExpenseManagementAppBackend.service.impl;

import com.ananya.ExpenseManagementAppBackend.dto.ExpenseDTO;
import com.ananya.ExpenseManagementAppBackend.entity.ExpenseEntity;
import com.ananya.ExpenseManagementAppBackend.repository.ExpenseRepository;
import com.ananya.ExpenseManagementAppBackend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for Expense module
 *
 * @author Ananya
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    /**
     * It will fetch all the expenses from the database
     *
     * @return list
     */
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<ExpenseEntity> expenseEntityList = expenseRepository.findAll();
        log.info("Printing the data from repository {}", expenseEntityList);

        return expenseEntityList.stream()
                .map(this::mapToExpenseDTO)
                .toList();

    }

    /**
     * Mapper method to convert expense entity to expense DTO
     *
     * @param expenseEntity
     * @return ExpenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }
}
