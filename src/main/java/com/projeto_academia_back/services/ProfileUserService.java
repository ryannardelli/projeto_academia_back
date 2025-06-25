package com.projeto_academia_back.services;

import com.projeto_academia_back.models.Profile;
import com.projeto_academia_back.models.Users;
import com.projeto_academia_back.repositories.ProfileRepository;
import com.projeto_academia_back.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileUserService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public boolean associateProfileToUser(int profileId, int userId) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);
        Optional<Users> userOptional = userRepository.findById(Long.valueOf(userId));

        if(profileOptional.isPresent() && userOptional.isPresent()) {
            Profile profile = profileOptional.get();
            Users user = userOptional.get();

            user.setProfile(profile);
            userRepository.save(user);

            return true;
        }

        return false;
    }

//    @Transactional
//    public boolean deleteProfileFromUser(Long userId) {
//        Optional<Profile> profileOptional = getProfileFromUser(userId);
//        if(profileOptional.isPresent()) {
//            Profile profile = profileOptional.get();
//            profileRepository.delete(profile);
//            return true;
//        }
//
//        return false;
//    }

    @Transactional
    public boolean deleteProfileFromUser(Long userId) {
        Optional<Users> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return false;
        }

        Users user = userOpt.get();
        Profile profile = user.getProfile();

        if (profile == null) {
            return false;
        }

        user.setProfile(null);
        userRepository.save(user); // desvincula o perfil do usuário

        return true;
    }


}
