package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Data
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue
    private Long id;

    private String nameDiet;
    private LocalDate dateBeginDiet;
    private LocalDate dateEndDiet;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
