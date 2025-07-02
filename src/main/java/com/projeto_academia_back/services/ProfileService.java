package com.projeto_academia_back.services;

import com.projeto_academia_back.models.Profile;
import com.projeto_academia_back.models.ProfileTraining;
import com.projeto_academia_back.repositories.ProfileRepository;
import com.projeto_academia_back.repositories.ProfileTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileTrainingRepository profileTrainingRepository;

    public List<Profile> showAll() {
        return profileRepository.findAll();
    }

    public Profile findById(Long id) {
        return (Profile) profileRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado."));
    }

    public Profile create(Profile profile) {
        // Salvar novo perfil
        Profile newProfile = profileRepository.save(profile);

        // Buscar um modelo com mesmo objetivo
        Profile modelProfile = profileRepository
                .findAll()
                .stream()
                .filter(p -> p.getObjective().equals(profile.getObjective()) && !p.getId().equals(newProfile.getId()))
                .findFirst()
                .orElse(null);

        // Copiar treinos se modelo encontrado
        if (modelProfile != null) {
            List<ProfileTraining> modelTraining = profileTrainingRepository.findAllByProfileId(modelProfile.getId());

            for (ProfileTraining profileTraining : modelTraining) {
                ProfileTraining newTraining = new ProfileTraining();
                newTraining.setProfile(newProfile);
                newTraining.setTraining(profileTraining.getTraining());
                profileTrainingRepository.save(newTraining);
            }
        }

        return newProfile;
    }


    public Profile update(Long id, Profile profileData) {
        Profile profile = findById(id);
        profile.setHeight(profileData.getHeight());
        profile.setWeight(profileData.getWeight());
        profile.setObjective(profileData.getObjective());
        return profileRepository.save(profile);
    }

    public void delete(Long id) {
        profileRepository.deleteById(Math.toIntExact(id));
    }
}
