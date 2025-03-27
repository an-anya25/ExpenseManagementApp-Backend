package com.ananya.ExpenseManagementAppBackend.service.impl;

import com.ananya.ExpenseManagementAppBackend.dto.ExpenseDTO;
import com.ananya.ExpenseManagementAppBackend.entity.ExpenseEntity;
import com.ananya.ExpenseManagementAppBackend.exceptions.ResourceNotFoundException;
import com.ananya.ExpenseManagementAppBackend.repository.ExpenseRepository;
import com.ananya.ExpenseManagementAppBackend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
     * It will fetch a single expense details from the database
     *
     * @param expenseId
     * @return ExpenseDTO
     */
    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity details {}", expenseEntity);
        return mapToExpenseDTO(expenseEntity);
    }

    /**
     * It will delete a single expense from the database
     *
     * @param expenseId
     * @return void
     */
    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity {}", expenseEntity);
        expenseRepository.delete(expenseEntity);
    }

    /**
     * It will save expense details to the database
     *
     * @param expenseDTO
     * @return ExpenseDTO
     */
    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseEntity = mapToExpenseEntity(expenseDTO);
        newExpenseEntity.setExpenseId(UUID.randomUUID().toString());
        newExpenseEntity = expenseRepository.save(newExpenseEntity);
        log.info("Printing the new expense entity details{}", newExpenseEntity);
        return mapToExpenseDTO(newExpenseEntity);
    }

    /**
     * It will save expense details to the database
     *
     * @param expenseId
     * @param expenseDTO
     * @return ExpenseDTO
     */
    @Override
    public ExpenseDTO updateExpenseByExpenseId(String expenseId, ExpenseDTO expenseDTO) {
        ExpenseEntity existingExpense = getExpenseEntity(expenseId);
        ExpenseEntity updatedExpenseEntity = mapToExpenseEntity(expenseDTO);
        updatedExpenseEntity.setId(existingExpense.getId());
        updatedExpenseEntity.setExpenseId(existingExpense.getExpenseId());
        updatedExpenseEntity.setCreatedAt(existingExpense.getCreatedAt());
        updatedExpenseEntity.setUpdatedAt(existingExpense.getUpdatedAt());
        updatedExpenseEntity = expenseRepository.save(updatedExpenseEntity);
        log.info("Printing the updated expense entity details{}", updatedExpenseEntity);
        return mapToExpenseDTO(updatedExpenseEntity);
    }

    /**
     * Mapper method to map values expense dto to expense entity
     *
     * @param expenseDTO
     * @return ExpenseEntity
     */
    private ExpenseEntity mapToExpenseEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseEntity.class);
    }

    /**
     * Mapper method to convert expense entity to expense dto
     *
     * @param expenseEntity
     * @return ExpenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }

    /**
     * Fetch the expense by the expenseId from the database
     *
     * @param expenseId
     * @return ExpenseEntity
     */
    private ExpenseEntity getExpenseEntity(String expenseId) {
        return expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for the expenseId " + expenseId));
    }
}
