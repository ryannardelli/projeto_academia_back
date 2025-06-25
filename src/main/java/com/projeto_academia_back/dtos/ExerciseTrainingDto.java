package com.projeto_academia_back.dtos;
import com.projeto_academia_back.models.ExerciseTraining;
import lombok.Data;

@Data
public class ExerciseTrainingDto {
    private Long idExerciseTraining;
    private TrainingDto training;
    private ExerciseDto exercise;
    private Integer series;
    private Integer repetitions;
    private Integer heavy;
    private Integer duration;
    private String urlVideo;
    private Integer restTime;

    public ExerciseTrainingDto(ExerciseTraining exerciseTraining) {
        this.idExerciseTraining = exerciseTraining.getId();
        this.training = new TrainingDto(exerciseTraining.getTraining());
        this.exercise = new ExerciseDto(exerciseTraining.getExercise());
        this.series = exerciseTraining.getSeries();
        this.repetitions = exerciseTraining.getRepetitions();
        this.heavy = exerciseTraining.getHeavy();
        this.duration = exerciseTraining.getDuration();
        this.urlVideo = exerciseTraining.getUrlVideo();
        this.restTime = exerciseTraining.getRestTime();
    }
}

