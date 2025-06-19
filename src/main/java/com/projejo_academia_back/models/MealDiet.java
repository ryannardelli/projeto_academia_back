package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
@Table(name = "dietMeal")
public class MealDiet {
    @Id @GeneratedValue
    private Long id;

    private LocalTime schedule;
    private Long mealOrder;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
}
