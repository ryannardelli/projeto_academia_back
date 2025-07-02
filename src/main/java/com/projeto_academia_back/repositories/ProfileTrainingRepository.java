package com.projeto_academia_back.repositories;

import com.projeto_academia_back.models.ProfileTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileTrainingRepository extends JpaRepository<ProfileTraining, Long> {
    List<ProfileTraining> findByProfile_Id(long profileId);
    Optional<ProfileTraining> findByTraining_IdAndProfile_Id(long profileId, long trainingId);
    List<ProfileTraining> findAllByProfileId(long trainingId);
}
