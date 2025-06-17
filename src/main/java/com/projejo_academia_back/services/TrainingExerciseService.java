package com.projejo_academia_back.services;

import com.projejo_academia_back.models.ExerciseTraining;
import com.projejo_academia_back.repositories.TrainingExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingExerciseService {
    @Autowired
    private TrainingExerciseRepository trainingExerciseRepository;

    public TrainingExerciseService(TrainingExerciseRepository trainingExerciseRepository) {
        this.trainingExerciseRepository = trainingExerciseRepository;
    }

    @Transactional
    public ExerciseTraining associateTrainingToExercise(ExerciseTraining exerciseTraining) {
        return trainingExerciseRepository.save(exerciseTraining);
    }

    @Transactional
    public void deleteTrainingExercise(ExerciseTraining exerciseTraining) {
        trainingExerciseRepository.delete(exerciseTraining);
    }

    @Transactional
    public List<ExerciseTraining> showPerId(Long trainingId) {
        return trainingExerciseRepository.findByTraining_Id(trainingId);
    }
}
