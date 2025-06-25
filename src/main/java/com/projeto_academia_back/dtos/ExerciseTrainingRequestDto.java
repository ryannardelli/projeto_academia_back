package com.projeto_academia_back.dtos;

import lombok.Data;

@Data
public class ExerciseTrainingRequestDto {
    private Integer series;
    private Integer repetitions;
    private Integer heavy;
    private Integer duration;
    private String urlVideo;
    private Integer restTime;
}

