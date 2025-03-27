package com.ananya.ExpenseManagementAppBackend.repository;

import com.ananya.ExpenseManagementAppBackend.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
