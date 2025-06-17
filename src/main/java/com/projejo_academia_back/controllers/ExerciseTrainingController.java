package com.projejo_academia_back.controllers;

import com.projejo_academia_back.dtos.ExerciseTrainingDto;
import com.projejo_academia_back.dtos.ExerciseTrainingRequestDto;
import com.projejo_academia_back.models.Exercise;
import com.projejo_academia_back.models.ExerciseTraining;
import com.projejo_academia_back.models.Training;
import com.projejo_academia_back.repositories.ExerciseRepository;
import com.projejo_academia_back.repositories.TrainingExerciseRepository;
import com.projejo_academia_back.repositories.TrainingRepository;
import com.projejo_academia_back.services.TrainingExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training/{trainingId}/exercise")
public class ExerciseTrainingController {

    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingExerciseRepository trainingExerciseRepository;
    private final TrainingExerciseService trainingExerciseService;

    public ExerciseTrainingController(ExerciseRepository exerciseRepository,
                                      TrainingRepository trainingRepository,
                                      TrainingExerciseRepository trainingExerciseRepository, TrainingExerciseService trainingExerciseService) {
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
        this.trainingExerciseRepository = trainingExerciseRepository;
        this.trainingExerciseService = trainingExerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseTrainingDto>> getExerciseTraining(@PathVariable("trainingId") Long trainingId) {
        List<ExerciseTraining> exerciseTrainings = trainingExerciseRepository.findByTraining_Id(trainingId);

        List<ExerciseTrainingDto> dtos = exerciseTrainings.stream()
                .map(ExerciseTrainingDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{exerciseId}")
    public ResponseEntity<String> associateExerciseToTraining(
            @PathVariable Long trainingId,
            @PathVariable Long exerciseId,
            @RequestBody ExerciseTrainingRequestDto requestDto) {

        Optional<Training> trainingOpt = trainingRepository.findById(Math.toIntExact(trainingId));
        Optional<Exercise> exerciseOpt = exerciseRepository.findById(exerciseId);

        if (trainingOpt.isEmpty() || exerciseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ExerciseTraining exerciseTraining = new ExerciseTraining();
        exerciseTraining.setExercise(exerciseOpt.get());
        exerciseTraining.setTraining(trainingOpt.get());

        exerciseTraining.setSeries(requestDto.getSeries());
        exerciseTraining.setRepetitions(requestDto.getRepetitions());
        exerciseTraining.setHeavy(requestDto.getHeavy());
        exerciseTraining.setDuration(requestDto.getDuration());
        exerciseTraining.setUrlVideo(requestDto.getUrlVideo());
        exerciseTraining.setRestTime(requestDto.getRestTime());

        trainingExerciseRepository.save(exerciseTraining);

        return ResponseEntity.ok("Exercício associado ao treino com sucesso.");
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<String> updateExerciseInTraining(
            @PathVariable Long trainingId,
            @PathVariable Long exerciseId,
            @RequestBody ExerciseTrainingRequestDto requestDto) {

        try {
            trainingExerciseService.updateExerciseTraining(trainingId, exerciseId, requestDto);
            return ResponseEntity.ok("Exercício atualizado no treino com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<String> removeExerciseFromTraining(@PathVariable Long trainingId, @PathVariable Long exerciseId) {
        Optional<ExerciseTraining> exerciseTraining = trainingExerciseRepository.findByTraining_IdAndExercise_Id(trainingId, exerciseId);

        if(exerciseTraining.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        trainingExerciseRepository.delete(exerciseTraining.get());
        return ResponseEntity.ok("Exercício removido do treino com sucesso.");
    }
}
