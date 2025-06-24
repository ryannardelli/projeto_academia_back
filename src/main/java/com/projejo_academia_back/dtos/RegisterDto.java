package com.projejo_academia_back.dtos;

import com.projejo_academia_back.enums.RoleUser;

public record RegisterDto(String email, String firstName, String lastName, String password, String phone, RoleUser role) {}
