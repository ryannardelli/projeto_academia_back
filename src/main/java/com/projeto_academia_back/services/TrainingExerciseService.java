package com.projeto_academia_back.services;

import com.projeto_academia_back.dtos.ExerciseTrainingRequestDto;
import com.projeto_academia_back.models.ExerciseTraining;
import com.projeto_academia_back.repositories.TrainingExerciseRepository;
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
    public ExerciseTraining updateExerciseTraining(Long trainingId, Long exerciseId, ExerciseTrainingRequestDto dto) {
        ExerciseTraining exerciseTraining = trainingExerciseRepository.findByTraining_IdAndExercise_Id(trainingId, exerciseId)
                .orElseThrow(() -> new RuntimeException("Associação não encontrada"));

        exerciseTraining.setSeries(dto.getSeries());
        exerciseTraining.setRepetitions(dto.getRepetitions());
        exerciseTraining.setHeavy(dto.getHeavy());
        exerciseTraining.setDuration(dto.getDuration());
        exerciseTraining.setUrlVideo(dto.getUrlVideo());
        exerciseTraining.setRestTime(dto.getRestTime());

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
