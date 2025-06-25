package com.projeto_academia_back.dtos;

import com.projeto_academia_back.models.Profile;

public class ProfileDto {
    public Long id_profile;
    public Double height;
    public Double weight;
    public String objective;
    public String imcStatus;

    public ProfileDto(Profile profile) {
        this.id_profile = profile.getId();
        this.height = profile.getHeight();
        this.weight = profile.getWeight();
        this.objective = profile.getObjective().name();
        this.imcStatus = profile.getImcStatus();
    }
}
