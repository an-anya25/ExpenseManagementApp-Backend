package com.ananya.ExpenseManagementAppBackend.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank(message = "Expense name is required")
    @Size(min = 3, message = "Expense name should be at least 3 characters")
    private String name;

    private String note;

    @NotBlank(message = "Expense category is required")
    @Size
    private String category;

    @NotNull(message = "Expense date is required")
    private Date date;

    @NotNull(message = "Expense amount is required")
    private BigDecimal amount;
}
