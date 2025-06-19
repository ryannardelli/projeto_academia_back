package com.projejo_academia_back.controllers;

import com.projejo_academia_back.models.Diet;
import com.projejo_academia_back.models.Profile;
import com.projejo_academia_back.repositories.DietRepository;
import com.projejo_academia_back.repositories.ProfileRepository;
import com.projejo_academia_back.services.ProfileDietService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileDietController {
    private final ProfileDietService profileDietService;
    private final ProfileRepository profileRepository;
    private final DietRepository dietRepository;

    public ProfileDietController(ProfileDietService profileDietService, ProfileRepository profileRepository, DietRepository dietRepository) {
        this.profileDietService = profileDietService;
        this.profileRepository = profileRepository;
        this.dietRepository = dietRepository;
    }

    @PostMapping("/{profileId}/diet/{dietId}")
    public ResponseEntity<String> associateDietToProfile(@PathVariable int profileId, @PathVariable Long dietId) {
        Optional<Profile> profileOpt = profileRepository.findById(profileId);
        Optional<Diet> dietOpt = dietRepository.findById(dietId);

        if(profileOpt.isEmpty() || dietOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = profileOpt.get();
        List<Diet> diets = profile.getDiets();
        if (diets == null) {
            diets = new ArrayList<>();
        }
        Diet diet = dietOpt.get();

        diet.setProfile(profile);

        diets.add(diet);
        profile.setDiets(diets);

        profileRepository.save(profile);

        return ResponseEntity.ok("Dieta associada ao perfil do usuário com sucesso.");
    }

    @GetMapping("/{profileId}/diet")
    public ResponseEntity<List<Diet>> getDietsByProfileId(@PathVariable int profileId) {
        Optional<Profile> profileOpt = profileRepository.findById(profileId);

        if(profileOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = profileOpt.get();

        if(profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile.getDiets());
    }

    @DeleteMapping("/{profileId}/diet/{dietId}")
    public ResponseEntity<String> removeDietFromProfile(
            @PathVariable int profileId,
            @PathVariable Long dietId) {

        Optional<Profile> profileOpt = profileRepository.findById(profileId);
        Optional<Diet> dietOpt = dietRepository.findById(dietId);

        if (profileOpt.isEmpty() || dietOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Perfil ou dieta não encontrados.");
        }

        Profile profile = profileOpt.get();
        Diet diet = dietOpt.get();

        if (!profile.getDiets().contains(diet)) {
            return ResponseEntity.status(400).body("Essa dieta não está associada a esse perfil.");
        }

        profile.getDiets().remove(diet);
        diet.setProfile(null);
        profileRepository.save(profile);

        return ResponseEntity.ok("Dieta removida do perfil com sucesso.");
    }

}
