//package com.projejo_academia_back.controllers;
//
//import com.projejo_academia_back.models.Profile;
//import com.projejo_academia_back.models.Users;
//import com.projejo_academia_back.repositories.ProfileRepository;
//import com.projejo_academia_back.repositories.UserRepository;
//import com.projejo_academia_back.services.ProfileUserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/user")
//public class UserProfileController {
//
//    private final UserRepository userRepository;
//    private final ProfileRepository profileRepository;
//    private final ProfileUserService profileUserService;
//
//    public UserProfileController(UserRepository userRepository, ProfileRepository profileRepository, ProfileUserService profileUserService) {
//        this.userRepository = userRepository;
//        this.profileRepository = profileRepository;
//        this.profileUserService = profileUserService;
//    }
//
//    @PostMapping("/{userId}/profile/{profileId}")
//    public ResponseEntity<String> associateProfileToUser(
//            @PathVariable Long userId,
//            @PathVariable Integer profileId) {
//
//        Optional<Users> userOpt = userRepository.findById(userId);
//        Optional<Profile> profileOpt = profileRepository.findById(profileId);
//
//        if (userOpt.isEmpty() || profileOpt.isEmpty()) {
//            return ResponseEntity.status(404).body("Usuário ou perfil não encontrados.");
//        }
//
//        Users user = userOpt.get();
//        Profile profile = profileOpt.get();
//
//        user.setProfile(profile);
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Perfil associado ao usuário com sucesso.");
//    }
//
//    @GetMapping("/{userId}/profile")
//    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
//        Optional<Users> userOpt = userRepository.findById(userId);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Users user = userOpt.get();
//        Profile profile = user.getProfile();
//
//        if (profile == null) {
//            return ResponseEntity.status(404).build();
//        }
//
//        return ResponseEntity.ok(profile);
//    }
//
//    @DeleteMapping("/{userId}/profile")
//    public ResponseEntity<String> removeProfileFromUser(@PathVariable Long userId) {
//        Optional<Users> userOpt = userRepository.findById(userId);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(404).body("Usuário não encontrado.");
//        }
//
//        Users user = userOpt.get();
//
//        if (user.getProfile() == null) {
//            return ResponseEntity.status(400).body("Usuário não possui um perfil associado.");
//        }
//
//        user.setProfile(null);
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Perfil removido do usuário com sucesso.");
//    }
//}

package com.projejo_academia_back.controllers;

import com.projejo_academia_back.models.Profile;
import com.projejo_academia_back.models.Users;
import com.projejo_academia_back.repositories.ProfileRepository;
import com.projejo_academia_back.repositories.UserRepository;
import com.projejo_academia_back.services.ProfileUserService;
import com.projejo_academia_back.services.UsersService;
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

    @GetMapping("/{userId}/profile")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
        Optional<Users> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Users user = userOpt.get();
        Profile profile = user.getProfile();

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.getProfile());
    }

    @DeleteMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<String> removeProfileFromUser(@PathVariable Long userId) {
        Optional<Users> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }

        Users user = userOpt.get();

        if (user.getProfile() == null) {
            return ResponseEntity.status(400).body("Usuário não possui um perfil associado.");
        }

        user.setProfile(null);
        userRepository.save(user);

        return ResponseEntity.ok("Perfil removido do usuário com sucesso.");
    }


//    @DeleteMapping("/{userId}/profile")
//    public ResponseEntity<String> removeProfileFromUser(@PathVariable Long userId) {
//        Optional<Users> userOpt = userRepository.findById(userId);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(404).body("Usuário não encontrado.");
//        }
//
//        Users user = userOpt.get();
//
//        if (user.getProfile() == null) {
//            return ResponseEntity.status(400).body("Usuário não possui um perfil associado.");
//        }
//
//        user.setProfile(null);
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Perfil removido do usuário com sucesso.");
//    }
}

