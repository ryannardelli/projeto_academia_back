package com.projejo_academia_back.services;


import com.projejo_academia_back.models.Exercise;
import com.projejo_academia_back.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise findById(Long id) {
        return (Exercise) exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado."));
    }

    public Exercise createExercise(Exercise exercise) {
        return (Exercise) exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise exerciseData) {
        Exercise exercise = findById(id);
        exercise.setName(exerciseData.getName());
        exercise.setMuscleGroup(exerciseData.getMuscleGroup());
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById((id));
    }
}
