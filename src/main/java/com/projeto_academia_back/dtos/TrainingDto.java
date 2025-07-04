package com.projeto_academia_back.dtos;

import com.projeto_academia_back.models.Training;

import java.time.LocalDate;

public class TrainingDto {
    public Long id_training;
    public String name;
    public LocalDate dateBeginTraining;
    public LocalDate dateEndTraining;

    public TrainingDto(Training training) {
        this.id_training = training.getId();
        this.name = training.getName();
        this.dateBeginTraining = training.getDateBeginTraining();
        this.dateEndTraining = training.getDateEndTraining();
    }
}
