package com.projeto_academia_back.models;

public class ObjectiveDto {
    private String label;
    private String value;

    public ObjectiveDto(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
