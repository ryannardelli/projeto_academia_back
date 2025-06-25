package com.projeto_academia_back.services;

import com.projeto_academia_back.models.ProfileTraining;
import com.projeto_academia_back.repositories.ProfileTrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileTrainingService {
    @Autowired
    private ProfileTrainingRepository profileTrainingRepository;

    public ProfileTrainingService(ProfileTrainingRepository profileTrainingRepository) {
        this.profileTrainingRepository = profileTrainingRepository;
    }

    @Transactional
    public ProfileTraining associateProfileToTraining(ProfileTraining profileTraining) {
        return profileTrainingRepository.save(profileTraining);
    }

    @Transactional
    public void deleteProfileTraining(ProfileTraining profileTraining) {
        profileTrainingRepository.delete(profileTraining);
    }

    @Transactional
    public List<ProfileTraining> getPerId(Long trainingId) {
        return profileTrainingRepository.findByProfile_Id(trainingId);
    }
}
