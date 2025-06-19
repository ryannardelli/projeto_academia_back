package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue
    private Long id;

    private String mealDescription;
    private LocalTime scheduleMeal;

    @ManyToOne
    private Diet diet;
}
