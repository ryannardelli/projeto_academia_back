package com.projeto_academia_back.controllers;

import com.projeto_academia_back.models.Profile;
import com.projeto_academia_back.models.Users;
import com.projeto_academia_back.repositories.ProfileRepository;
import com.projeto_academia_back.repositories.UserRepository;
import com.projeto_academia_back.services.ProfileUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserProfileController {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ProfileUserService profileUserService;

    public UserProfileController(UserRepository userRepository, ProfileRepository profileRepository, ProfileUserService profileUserService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profileUserService = profileUserService;
    }

    @PostMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<String> associateProfileToUser(
            @PathVariable Long userId,
            @PathVariable Integer profileId) {

        Optional<Users> userOpt = userRepository.findById(userId);
        Optional<Profile> profileOpt = profileRepository.findById(profileId);

        if (userOpt.isEmpty() || profileOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário ou perfil não encontrados.");
        }

        Users user = userOpt.get();
        Profile profile = profileOpt.get();

        user.setProfile(profile);

        userRepository.save(user);

        return ResponseEntity.ok("Perfil associado ao usuário com sucesso.");
    }

    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<String> removeProfileFromUser(@PathVariable Long userId) {
        boolean removed = profileUserService.deleteProfileFromUser(userId);

        if (!removed) {
            return ResponseEntity.status(404).body("Usuário não encontrado ou não possui perfil.");
        }

        return ResponseEntity.ok("Perfil removido do usuário com sucesso.");
    }

}

