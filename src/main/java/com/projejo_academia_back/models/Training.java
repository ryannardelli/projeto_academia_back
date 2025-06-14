package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "training")
public class Training {
    @Id @GeneratedValue
    private Long id_training;

    private String name;
    private LocalDate dateBeginTraining;
    private LocalDate dateEndTraining;

    @ManyToOne
    private Profile profile;
}
