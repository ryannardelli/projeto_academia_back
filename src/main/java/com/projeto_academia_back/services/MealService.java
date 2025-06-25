package com.projeto_academia_back.services;

import com.projeto_academia_back.models.Meal;
import com.projeto_academia_back.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    public Meal findMealById(Long id) {
        return (Meal) mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refeição não encontrada"));
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal updateMeal(Long id, Meal mealData) {
        Meal meal = findMealById(id);
        meal.setMealDescription(mealData.getMealDescription());
        meal.setScheduleMeal(mealData.getScheduleMeal());
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
