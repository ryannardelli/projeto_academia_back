package com.projejo_academia_back.dtos;

import com.projejo_academia_back.models.ProfileTraining;
import lombok.Data;

@Data
public class TrainingProfileDto {
    private Long idTrainingProfile;
    private ProfileDto profile;
    private TrainingDto training;

    public TrainingProfileDto(ProfileTraining profileTraining) {
        this.idTrainingProfile = profileTraining.getId();
        this.profile = new ProfileDto(profileTraining.getProfile());
        this.training = new TrainingDto(profileTraining.getTraining());
    }
}

