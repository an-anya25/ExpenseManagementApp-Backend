package com.ananya.ExpenseManagementAppBackend.service.impl;

import com.ananya.ExpenseManagementAppBackend.dto.ProfileDTO;
import com.ananya.ExpenseManagementAppBackend.entity.ProfileEntity;
import com.ananya.ExpenseManagementAppBackend.repository.ProfileRepository;
import com.ananya.ExpenseManagementAppBackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service interface for Profile module
 *
 * @author Ananya
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ModelMapper modelMapper;

    /**
     * It will save profile details to the database
     *
     * @param profileDTO
     * @return ProfileDTO
     */
    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        profileEntity = profileRepository.save(profileEntity);
        log.info("Printing the profile entity details {}", profileEntity);
        return mapToProfileDTO(profileEntity);
    }

    /**
     * Mapper method to map profile entity to profile dto
     *
     * @param profileEntity
     * @return ProfileDTO
     */
    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    /**
     * Mapper method to map profile dto to profile entity
     *
     * @param profileDTO
     * @return ProfileEntity
     */
    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
