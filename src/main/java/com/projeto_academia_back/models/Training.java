package com.projeto_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "training")
public class Training {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private LocalDate dateBeginTraining;
    private LocalDate dateEndTraining;

    @ManyToOne
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
