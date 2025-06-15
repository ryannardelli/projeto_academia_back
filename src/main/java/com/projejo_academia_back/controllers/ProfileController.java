package com.projejo_academia_back.controllers;


import com.projejo_academia_back.dtos.ProfileDto;
import com.projejo_academia_back.models.Profile;
import com.projejo_academia_back.repositories.ProfileRepository;
import com.projejo_academia_back.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping()
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProfileDto findByIdDTO(@PathVariable int id) {
        Profile profile = profileService.findById((long) id);
        return new ProfileDto(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile newProfile = profileService.create(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
    }

    @PutMapping("/{id}")
    public Profile update(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.update(id, profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
