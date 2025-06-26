package com.projeto_academia_back.dtos;

import com.projeto_academia_back.enums.RoleUser;


public class UsersDto {
    public Long id_user;
    public String firstName;
    public String lastName;
    public String email;
    public String urlPicture;
    public RoleUser role;
    public boolean profileConfigured;
    public ProfileDto profile;
}
