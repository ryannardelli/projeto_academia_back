package com.projejo_academia_back.services;

import com.projejo_academia_back.dtos.DietDto;
import com.projejo_academia_back.models.Diet;
import com.projejo_academia_back.models.Profile;
import com.projejo_academia_back.repositories.DietRepository;
import com.projejo_academia_back.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileDietService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    DietRepository dietRepository;

    @Transactional
    public boolean associateProfileToDiet(int profileId, Long dietId) {
        Optional<Profile> profileOpt = profileRepository.findById(profileId);
        Optional<Diet> dietOpt = dietRepository.findById(dietId);

        if (profileOpt.isPresent() && dietOpt.isPresent()) {
            Diet diet = dietOpt.get();
            Profile profile = profileOpt.get();

            diet.setProfile(profile);
            dietRepository.save(diet);

            return true;
        }

        return false;
    }

    public Optional<List<Diet>> getDietsOfProfile(int profileId) {
        return profileRepository.findById((int) profileId)
                .map(Profile::getDiets);
    }


    @Transactional
    public boolean deleteProfileFromDiet(int profileId) {
        Optional<Profile> profileOpt = profileRepository.findById(profileId);
        if(profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            profile.setDiets(null);
            profileRepository.save(profile);
            return true;
        }

        return false;
    }
}
