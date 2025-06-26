package com.projeto_academia_back.dtos;

import com.projeto_academia_back.enums.RoleUser;

public record RegisterDto(String email, String firstName, String lastName, String password, String phone, RoleUser role, boolean profileConfigured) {}
