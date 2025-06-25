package com.projeto_academia_back.controllers;

import com.projeto_academia_back.dtos.TrainingProfileDto;
import com.projeto_academia_back.models.*;
import com.projeto_academia_back.repositories.ProfileRepository;
import com.projeto_academia_back.repositories.ProfileTrainingRepository;
import com.projeto_academia_back.repositories.TrainingRepository;
import com.projeto_academia_back.services.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/profile/{profileId}/training")
public class ProfileTrainingController {
    private final ProfileRepository profileRepository;
    private final TrainingRepository trainingRepository;
    private final ProfileTrainingRepository profileTrainingRepository;

    public ProfileTrainingController(ProfileRepository profileRepository, TrainingRepository trainingRepository, ProfileTrainingRepository profileTrainingRepository, TrainingService trainingService) {
        this.profileRepository = profileRepository;
        this.trainingRepository = trainingRepository;
        this.profileTrainingRepository = profileTrainingRepository;
    }

    @GetMapping
    public ResponseEntity<List<TrainingProfileDto>> getProfileTrainings(@PathVariable Long profileId) {
        List<ProfileTraining> profileTrainings = profileTrainingRepository.findByProfile_Id(profileId);

        List<TrainingProfileDto> dtos = profileTrainings.stream()
                .map(TrainingProfileDto::new)
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(dtos);
    }


    @PostMapping("/{trainingId}")
    public ResponseEntity<String> associateExerciseToTraining(
            @PathVariable Long trainingId,
            @PathVariable Long profileId) {

        Optional<Training> trainingOpt = trainingRepository.findById(Math.toIntExact(trainingId));
        Optional<Profile> profileOpt = profileRepository.findById(Math.toIntExact(profileId));

        if (trainingOpt.isEmpty() || profileOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProfileTraining profileTraining = new ProfileTraining();

        profileTraining.setTraining(trainingOpt.get());
        profileTraining.setProfile(profileOpt.get());

        profileTrainingRepository.save(profileTraining);

        return ResponseEntity.ok("Treino associado ao perfil com sucesso.");
    }


    @DeleteMapping("/{trainingId}")
    public ResponseEntity<String> removeExerciseFromTraining(@PathVariable Long trainingId, @PathVariable Long exerciseId) {
        Optional<ProfileTraining> exerciseTraining = profileTrainingRepository.findByTraining_IdAndProfile_Id(trainingId, exerciseId);

        if(exerciseTraining.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        profileTrainingRepository.delete(exerciseTraining.get());
        return ResponseEntity.ok("Perfil removido do treino com sucesso.");
    }

}
