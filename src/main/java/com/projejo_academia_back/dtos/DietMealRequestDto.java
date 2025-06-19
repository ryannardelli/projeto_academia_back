package com.projejo_academia_back.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DietMealRequestDto {
   private LocalTime schedule;
   private Long mealOrder;
}
