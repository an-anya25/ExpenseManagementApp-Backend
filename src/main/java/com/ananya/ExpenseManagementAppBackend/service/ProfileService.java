package com.ananya.ExpenseManagementAppBackend.service;

import com.ananya.ExpenseManagementAppBackend.dto.ProfileDTO;

/**
 * Service interface for Profile module
 *
 * @author Ananya
 */
public interface ProfileService {

    /**
     * It will save profile details to the database
     *
     * @param profileDTO
     * @return ProfileDTO
     */
    ProfileDTO createProfile(ProfileDTO profileDTO);

}
