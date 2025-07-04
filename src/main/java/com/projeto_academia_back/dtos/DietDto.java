package com.projeto_academia_back.dtos;

import com.projeto_academia_back.models.Diet;

import java.time.LocalDate;

public class DietDto {
    public Long IdDiet;
    public String nameDiet;
    public LocalDate dateBeginDiet;
    public LocalDate dateEndDiet;

    public DietDto(Diet diet) {
        this.IdDiet = diet.getId();
        this.nameDiet = diet.getNameDiet();
        this.dateBeginDiet = diet.getDateBeginDiet();
        this.dateEndDiet = diet.getDateEndDiet();
    }
}
