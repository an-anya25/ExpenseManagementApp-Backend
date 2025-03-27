package com.ananya.ExpenseManagementAppBackend.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private String profileId;

    private String name;

    private String email;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
