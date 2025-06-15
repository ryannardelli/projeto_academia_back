package com.projejo_academia_back.dtos;

import com.projejo_academia_back.enums.RoleUser;

public record RegisterDto(String email, String password, RoleUser role) {}
