package com.projeto_academia_back.dtos;

import com.projeto_academia_back.models.Exercise;

public class ExerciseDto {
    public Long id_exercise;
    public String name;
    public String muscleGroup;

    public ExerciseDto(Exercise exercise) {
       this.id_exercise = exercise.getId();
       this.name = exercise.getName();
       this.muscleGroup = exercise.getMuscleGroup();
    }
}
