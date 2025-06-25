package com.projeto_academia_back.dtos;

import com.projeto_academia_back.models.MealDiet;
import lombok.Data;

import java.time.LocalTime;

@Data
public class DietMealDto {
    private Long idDietMeal;
    private MealDto meal;
    private DietDto diet;
    private LocalTime schedule;
    private Long mealOrder;

    public DietMealDto(MealDiet dietMeal) {
        this.idDietMeal = dietMeal.getId();
        this.meal = new MealDto(dietMeal.getMeal());
        this.diet = new DietDto(dietMeal.getDiet());
        this.schedule = dietMeal.getSchedule();
        this.mealOrder = dietMeal.getMealOrder();
    }
}