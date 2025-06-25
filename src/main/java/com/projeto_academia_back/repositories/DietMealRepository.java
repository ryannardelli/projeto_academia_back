package com.projeto_academia_back.repositories;

import com.projeto_academia_back.models.MealDiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietMealRepository extends JpaRepository<MealDiet, Long> {
    List<MealDiet> findByMeal_Id(Long mealId);
    List<MealDiet> findByDiet_IdAndMeal_Id(Long dietId, Long mealId);
}
