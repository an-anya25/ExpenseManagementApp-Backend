package com.ananya.ExpenseManagementAppBackend.controller;

import com.ananya.ExpenseManagementAppBackend.dto.ExpenseDTO;
import com.ananya.ExpenseManagementAppBackend.io.ExpenseResponse;
import com.ananya.ExpenseManagementAppBackend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * It will fetch all th expenses from the database
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
     * Mapper method for converting expense dto object to expense response
     *
     * @param expenseDTO
     * @return ExpenseResponse
     */
    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }

    ;
}
