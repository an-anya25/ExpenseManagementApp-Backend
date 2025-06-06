package com.ananya.ExpenseManagementAppBackend.controller;

import com.ananya.ExpenseManagementAppBackend.dto.ProfileDTO;
import com.ananya.ExpenseManagementAppBackend.io.ProfileRequest;
import com.ananya.ExpenseManagementAppBackend.io.ProfileResponse;
import com.ananya.ExpenseManagementAppBackend.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is controller class for Profile module
 *
 * @author Ananya
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;

    private final ProfileService profileService;

    /**
     * It will save a profile to the database
     *
     * @param profileRequest
     * @return ProfileResponse
     **/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API POST /register called {}", profileRequest);
        ProfileDTO profileDTO = mapToProfileDTO(profileRequest);
        profileDTO = profileService.createProfile(profileDTO);
        log.info("Printing the profile dto details {}", profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    /**
     * Mapper method to map profile dto to profile response
     *
     * @param profileDTO
     * @return ProfileResponse
     */
    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileResponse.class);
    }

    /**
     * Mapper method to map profile request to profile dto
     *
     * @param profileRequest
     * @return ProfileDTO
     */
    private ProfileDTO mapToProfileDTO(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

}
