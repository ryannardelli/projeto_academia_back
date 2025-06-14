package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "exerciseTraining")
public class ExerciseTraining {
    @Id @GeneratedValue
    private Long id_exerciseTraining;

    private Integer series;
    private Integer repetitions;
    private Integer heavy;
    private Integer duration;

    @ManyToOne
    private Training training;

    @ManyToOne
    private Exercise exercise;
}
