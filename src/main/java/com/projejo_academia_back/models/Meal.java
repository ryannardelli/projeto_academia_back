package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue
    private Long id_meal;

    private String mealDescription;
    private LocalDate scheduleMeal;

    @ManyToOne
    private Diet diet;
}
