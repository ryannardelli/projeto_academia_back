package com.projejo_academia_back.services;

import com.projejo_academia_back.dtos.DietMealRequestDto;
import com.projejo_academia_back.models.MealDiet;
import com.projejo_academia_back.repositories.DietMealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DietMealService {
    @Autowired
    private DietMealRepository dietMealRepository;

    public DietMealService(DietMealRepository dietMealRepository) {
        this.dietMealRepository = dietMealRepository;
    }

    @Transactional
    public MealDiet associateDietMeal(MealDiet dietMeal) {
        return dietMealRepository.save(dietMeal);
    }

    @Transactional
    public MealDiet updateDietMeal(Long mealId, Long dietId, @RequestBody DietMealRequestDto dto) {
        List<MealDiet> dietMeals = dietMealRepository.findByDiet_IdAndMeal_Id(dietId, mealId);
        MealDiet dietMeal = dietMeals.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Associação não encontrada"));


            dietMeal.setSchedule(dto.getSchedule());
            dietMeal.setMealOrder(dto.getMealOrder());
            return dietMealRepository.save(dietMeal);
    }

    @Transactional
    public void deleteDietMeal(MealDiet dietMeal) {
        dietMealRepository.delete(dietMeal);
    }

    @Transactional
    public List<MealDiet> getPerId(Long mealId) {
        return dietMealRepository.findByMeal_Id(mealId);
    }
}
