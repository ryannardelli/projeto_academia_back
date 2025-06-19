package com.projejo_academia_back.controllers;

import com.projejo_academia_back.dtos.MealDto;
import com.projejo_academia_back.models.Meal;
import com.projejo_academia_back.repositories.MealRepository;
import com.projejo_academia_back.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @Autowired
    private MealRepository mealRepository;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @GetMapping("/{id}")
    public MealDto getMealById(@PathVariable Long id) {
        Meal meal = mealService.findMealById(id);
        return new MealDto(meal);
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Meal newMeal = mealService.createMeal(meal);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMeal);
    }

    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        return mealService.updateMeal(id, meal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meal> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}
