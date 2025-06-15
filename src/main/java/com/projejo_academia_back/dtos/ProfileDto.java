package com.projejo_academia_back.dtos;

import com.projejo_academia_back.models.Profile;

public class ProfileDto {
    public Long id_profile;
    public Double height;
    public Double weight;
    public String objective;

    public ProfileDto(Profile profile) {
        this.id_profile = profile.getId_profile();
        this.height = profile.getHeight();
        this.weight = profile.getWeight();
        this.objective = profile.getObjective();
    }
}
