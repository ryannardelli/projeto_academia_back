package com.projejo_academia_back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "progress")
public class Progress {
    @Id @GeneratedValue
    private Long id_progress;

    private Double weightProgress;
    private Double heightProgress;
    private String meausre;
    private LocalDateTime dateProgress;
}
