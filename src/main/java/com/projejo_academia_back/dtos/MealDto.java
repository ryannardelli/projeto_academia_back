package com.projejo_academia_back.dtos;

import com.projejo_academia_back.models.Meal;

import java.time.LocalTime;

public class MealDto {
    public Long idMeal;
    public String mealDescription;
    public LocalTime scheduleMeal;

    public MealDto(Meal meal) {
        this.idMeal = meal.getId();
        this.mealDescription = meal.getMealDescription();
        this.scheduleMeal = meal.getScheduleMeal();
    }
}
