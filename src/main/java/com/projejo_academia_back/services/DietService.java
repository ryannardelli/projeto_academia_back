package com.projejo_academia_back.services;

import com.projejo_academia_back.models.Diet;
import com.projejo_academia_back.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {
    @Autowired
    private DietRepository dietRepository;

    public List<Diet> getDiets() {
        return dietRepository.findAll();
    }

    public Diet findById(Long id) {
        return (Diet) dietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diet não encontrada."));
    }

    public Diet createDiet(Diet diet) {return dietRepository.save(diet);}

    public Diet updateDiet(Long id, Diet dietData) {
        Diet diet = findById(id);
        diet.setNameDiet(dietData.getNameDiet());
        diet.setDateBeginDiet(dietData.getDateBeginDiet());
        diet.setDateEndDiet(dietData.getDateEndDiet());
        return dietRepository.save(diet);
    }

    public void deleteDiet(Long id) {dietRepository.deleteById(id);}
}
