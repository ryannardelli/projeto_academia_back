package com.projejo_academia_back.models;

import com.projejo_academia_back.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue
    private Long id_user;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
}
