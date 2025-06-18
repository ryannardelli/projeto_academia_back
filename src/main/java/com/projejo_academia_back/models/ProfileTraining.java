package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profileTraining")
public class ProfileTraining {
    @Id @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
