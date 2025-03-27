package com.ananya.ExpenseManagementAppBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

    private String profileId;

    private String name;

    private String email;

    private String password;

    private Timestamp createdAt;
    
    private Timestamp updatedAt;
}
