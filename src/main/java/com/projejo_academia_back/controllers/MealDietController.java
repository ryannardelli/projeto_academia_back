package com.projejo_academia_back.controllers;

import com.projejo_academia_back.dtos.DietMealDto;
import com.projejo_academia_back.dtos.DietMealRequestDto;
import com.projejo_academia_back.models.Diet;
import com.projejo_academia_back.models.MealDiet;
import com.projejo_academia_back.models.Meal;
import com.projejo_academia_back.repositories.DietMealRepository;
import com.projejo_academia_back.repositories.DietRepository;
import com.projejo_academia_back.repositories.MealRepository;
import com.projejo_academia_back.services.DietMealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meal/{mealId}/diet")
public class MealDietController {

    private final DietMealRepository dietMealRepository;
    private final DietMealService dietMealService;
    private final DietRepository dietRepository;
    private final MealRepository mealRepository;

    public MealDietController(DietMealRepository dietMealRepository, DietRepository dietRepository, MealRepository mealRepository, DietMealService dietMealService) {
        this.dietMealRepository = dietMealRepository;
        this.dietMealService = dietMealService;
        this.dietRepository = dietRepository;
        this.mealRepository = mealRepository;
    }

    @GetMapping
    public ResponseEntity<List<DietMealDto>> getDietMeal(@PathVariable Long mealId) {
            List<MealDiet> dietMeal = dietMealRepository.findByMeal_Id(mealId);

            List<DietMealDto> dtos = dietMeal.stream()
                    .map(DietMealDto::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{dietId}")
    public ResponseEntity<String> associateMealToDiet(@PathVariable Long mealId, @PathVariable Long dietId, @RequestBody DietMealRequestDto dietMealRequestDto) {
        Optional<Diet> dietOptional = dietRepository.findById(dietId);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);

        if(dietOptional.isEmpty() || mealOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MealDiet dietMeal = new MealDiet();
        dietMeal.setDiet(dietOptional.get());
        dietMeal.setMeal(mealOptional.get());

        dietMeal.setSchedule(dietMealRequestDto.getSchedule());
        dietMeal.setMealOrder(dietMealRequestDto.getMealOrder());

        dietMealRepository.save(dietMeal);
        return ResponseEntity.ok("Dieta associada a refeição com sucesso.");
    }

    @PutMapping("/{dietId}")
    public ResponseEntity<String> updateDietInMeal(@PathVariable Long mealId, @PathVariable Long dietId, @RequestBody DietMealRequestDto dietMealRequestDto) {
       try {
           dietMealService.updateDietMeal(mealId, dietId, dietMealRequestDto);
           return ResponseEntity.ok("Dieta atualizada na Refeição com sucesso.");
       } catch(RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{dietId}")
    public ResponseEntity<String> removeDietFromMeal(@PathVariable Long dietId, @PathVariable Long mealId) {
        List<MealDiet> dietMeal = dietMealRepository.findByDiet_IdAndMeal_Id(dietId, mealId);

        if (dietMeal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dietMealRepository.delete(dietMeal.get(0));
        return ResponseEntity.ok("Dieta removida da refeição com sucesso.");
    }

}
