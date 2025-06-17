package com.projejo_academia_back.controllers;

import com.projejo_academia_back.dtos.TrainingDto;
import com.projejo_academia_back.models.Training;
import com.projejo_academia_back.repositories.TrainingRepository;
import com.projejo_academia_back.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainingRepository trainingRepository;

    @GetMapping
    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    @GetMapping("/{id}")
    public TrainingDto findById(@PathVariable Long id) {
        Training training = trainingService.findById(id);
        return new TrainingDto(training);
    }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        Training newTraining = trainingService.createTraining(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTraining);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody Training training) {
        return trainingService.updateTraining(id, training);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Training> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
