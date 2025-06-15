package com.projejo_academia_back.enums;

public enum RoleUser {
    Aluno("Aluno"),
    Personal("Personal"),
    Nutricionista("Nutricionista"),
    Administrador("Administrador");

    private final String role;

    RoleUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
