package com.projejo_academia_back.services;

import com.projejo_academia_back.models.Training;
import com.projejo_academia_back.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> showAllTraining() {
        return trainingRepository.findAll();
    }

    public Training findById(Long id) {
        return(Training) trainingRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Treino não encontrado."));
    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training updateTraining(Long id, Training trainingData) {
        Training training = findById(id);
        training.setName(trainingData.getName());
        training.setDateBeginTraining(trainingData.getDateBeginTraining());
        training.setDateEndTraining(trainingData.getDateEndTraining());
        return trainingRepository.save(training);
    }

    public void deleteTraining(Long id) {
        trainingRepository.deleteById(Math.toIntExact(id));
    }
}
