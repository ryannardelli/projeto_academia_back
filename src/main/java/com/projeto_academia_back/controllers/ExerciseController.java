package com.projeto_academia_back.controllers;

import com.projeto_academia_back.dtos.ExerciseDto;
import com.projeto_academia_back.models.Exercise;
import com.projeto_academia_back.repositories.ExerciseRepository;
import com.projeto_academia_back.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseDto findExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        return new ExerciseDto(exercise);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise newExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
