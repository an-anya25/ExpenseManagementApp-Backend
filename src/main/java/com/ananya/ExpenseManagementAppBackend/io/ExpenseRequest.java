package com.ananya.ExpenseManagementAppBackend.io;

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

    private String name;

    private String note;

    private String category;

    private Date date;

    private BigDecimal amount;
}
