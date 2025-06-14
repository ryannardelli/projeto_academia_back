package com.projejo_academia_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "member")
public class Profile {
    @Id
    @GeneratedValue
    private Long id_profile;

    private Double height;
    private Double weight;
    private String objective;

    @OneToOne
    private Users users;
}
