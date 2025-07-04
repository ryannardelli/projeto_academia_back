package com.projeto_academia_back.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projeto_academia_back.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id @GeneratedValue
    private Long id_user;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String picture;
    private String phone;
    private boolean profileConfigured = false;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public Users(String email, String firstName, String lastName, String password, RoleUser roleUser, String phone, boolean profileConfigured) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roleUser = roleUser;
        this.phone = phone;
        this.profileConfigured = profileConfigured;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roleUser == RoleUser.Administrador) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}