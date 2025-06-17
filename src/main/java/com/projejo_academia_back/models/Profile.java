package com.projejo_academia_back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id_profile;

    private Double height;
    private Double weight;
    private String objective;

    @JsonBackReference
    @OneToOne(mappedBy = "profile")
    private Users users;

}
