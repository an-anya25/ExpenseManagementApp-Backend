package com.ananya.ExpenseManagementAppBackend.controller;

import com.ananya.ExpenseManagementAppBackend.dto.ExpenseDTO;
import com.ananya.ExpenseManagementAppBackend.io.ExpenseRequest;
import com.ananya.ExpenseManagementAppBackend.io.ExpenseResponse;
import com.ananya.ExpenseManagementAppBackend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for Expense module
 *
 * @author Ananya
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;

    private final ModelMapper modelMapper;

    /**
     * It will fetch all the expenses from the database
     *
     * @return list
     **/
    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");

        List<ExpenseDTO> expenseDTOList = expenseService.getAllExpenses();
        log.info("Printing the data from the service {}", expenseDTOList);

        return expenseDTOList.stream()
                .map(this::mapToExpenseResponse)
                .toList();
    }

    /**
     * It will fetch a single expense from the database
     *
     * @param expenseId
     * @return ExpenseResponse
     **/
    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        log.info("API GET /expenses/{} called", expenseId);

        ExpenseDTO expenseDTO = expenseService.getExpenseByExpenseId(expenseId);
        log.info("Printing the expense details {}", expenseDTO);

        return mapToExpenseResponse(expenseDTO);
    }

    /**
     * It will delete a single expense from the database
     *
     * @param expenseId
     * @return void
     **/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{expenseId}")
    public void deleteExpenseByExpenseId(@PathVariable String expenseId) {
        log.info("API DELETE /expenses/{} called", expenseId);
        expenseService.deleteExpenseByExpenseId(expenseId);
    }

    /**
     * It will save a single expense to the database
     *
     * @param expenseRequest
     * @return ExpenseResponse
     **/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expenses")
    public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest) {
        log.info("API POST /expenses called {}", expenseRequest);
        ExpenseDTO expenseDTO = mapToExpenseDTO(expenseRequest);
        expenseDTO = expenseService.saveExpenseDetails(expenseDTO);
        log.info("Printing the expenseDTO {}", expenseDTO);
        return mapToExpenseResponse(expenseDTO);
    }

    /**
     * It will update the expense details to the database
     *
     * @param expenseId
     * @param updateRequest
     * @return ExpenseResponse
     **/
    @PutMapping("/expenses/{expenseId}")
    public ExpenseResponse updateExpenseDetails(@PathVariable String expenseId, @RequestBody ExpenseRequest updateRequest) {
        log.info("API PUT /expenses/{} called request body {}", expenseId, updateRequest);
        ExpenseDTO updatedExpenseDTO = mapToExpenseDTO(updateRequest);
        updatedExpenseDTO = expenseService.updateExpenseByExpenseId(expenseId, updatedExpenseDTO);
        log.info("Printing the updated expenseDTO {}", updatedExpenseDTO);
        return mapToExpenseResponse(updatedExpenseDTO);
    }

    /**
     * Mapper method to map expense request to expense dto
     *
     * @param expenseRequest
     * @return ExpenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(ExpenseRequest expenseRequest) {
        return modelMapper.map(expenseRequest, ExpenseDTO.class);
    }

    /**
     * Mapper method for converting expense dto object to expense response
     *
     * @param expenseDTO
     * @return ExpenseResponse
     */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }

}
