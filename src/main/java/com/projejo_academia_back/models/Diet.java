package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue
    private Long id_diet;

    private String nameDiet;
    private LocalDate dateBeginDiet;
    private LocalDate dateEndDiet;

    @ManyToOne
    private Profile profile;
}
