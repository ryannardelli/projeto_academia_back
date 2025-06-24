package com.projejo_academia_back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projejo_academia_back.enums.Objective;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    private Double height;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private Objective objective;

    private Double imc;
    private String imcStatus;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Diet> diets;

    @PrePersist
    @PreUpdate
    public void calculateImc() {
        if (height != null && weight != null && height > 0) {
            double alturaMetros = (height > 10) ? height / 100.0 : height;
            this.imc = weight / (alturaMetros * alturaMetros);
            this.imcStatus = getImcStatus(this.imc);
        }
    }

    private String getImcStatus(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidade grau 1";
        } else if (imc < 40) {
            return "Obesidade grau 2";
        } else {
            return "Obesidade grau 3";
        }
    }
}
