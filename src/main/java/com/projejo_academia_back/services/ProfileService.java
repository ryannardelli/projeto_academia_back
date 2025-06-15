package com.projejo_academia_back.services;

import com.projejo_academia_back.models.Profile;
import com.projejo_academia_back.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> showAll() {
        return profileRepository.findAll();
    }

    public Profile findById(Long id) {
        return (Profile) profileRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado."));
    }

    public Profile create(Profile profile) {
        return (Profile) profileRepository.save(profile);
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
