package com.projeto_academia_back.controllers;

import com.projeto_academia_back.dtos.DietDto;
import com.projeto_academia_back.models.Diet;
import com.projeto_academia_back.repositories.DietRepository;
import com.projeto_academia_back.services.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diet")
public class DietController {
    @Autowired
    private DietService dietService;

    @Autowired
    private DietRepository dietRepository;

    @GetMapping
    public List<Diet> getAll() {
        return dietRepository.findAll();
    }

    @GetMapping("/{id}")
    public DietDto getById(@PathVariable Long id) {
        Diet diet = dietService.findById(id);
        return new DietDto(diet);
    }

    @PostMapping
    public ResponseEntity<Diet> createDiet(@RequestBody Diet diet) {
        Diet newDiet = dietService.createDiet(diet);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDiet);
    }

    @PutMapping("/{id}")
    public Diet updateDiet(@PathVariable Long id, @RequestBody Diet diet) {
        return dietService.updateDiet(id, diet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Diet> deleteDiet(@PathVariable Long id) {
        dietService.deleteDiet(id);
        return ResponseEntity.noContent().build();
    }

}
