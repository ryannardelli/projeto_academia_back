package com.projeto_academia_back.repositories;

import com.projeto_academia_back.models.ExerciseTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingExerciseRepository extends JpaRepository<ExerciseTraining, Long> {
    List<ExerciseTraining> findByTraining_Id(long trainingId);
    Optional<ExerciseTraining> findByTraining_IdAndExercise_Id(long trainingId, long exerciseId);
}
