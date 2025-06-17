package com.projejo_academia_back.dtos;
//
//import com.projejo_academia_back.models.ExerciseTraining;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Data
//public class ExerciseTrainingDto {
//    private Long exerciseId;
//    private Long trainingId;
//    private String trainingName;
//    private LocalDate dateBeginTraining;
//    private LocalDate dateEndTraining;
//    private String nameExercise;
//    private String muscleGroupExercise;
//    private Integer series;
//    private Integer repetitions;
//    private Integer heavy;
//    private Integer duration;
//    private String urlVideo;
//    private Integer restTime;
//
//    public ExerciseTrainingDto(ExerciseTraining exerciseTraining) {
//        this.exerciseId = exerciseTraining.getExercise().getId();
//        this.trainingId = exerciseTraining.getTraining().getId();
//        this.trainingName = exerciseTraining.getTraining().getName();
//        this.dateBeginTraining = exerciseTraining.getTraining().getDateBeginTraining();
//        this.dateEndTraining = exerciseTraining.getTraining().getDateEndTraining();
//        this.nameExercise = exerciseTraining.getExercise().getName();
//        this.muscleGroupExercise = exerciseTraining.getExercise().getMuscleGroup();
//        this.series = exerciseTraining.getSeries();
//        this.repetitions = exerciseTraining.getRepetitions();
//        this.heavy = exerciseTraining.getHeavy();
//        this.duration = exerciseTraining.getDuration();
//        this.urlVideo = exerciseTraining.getUrlVideo();
//        this.restTime = exerciseTraining.getRestTime();
//    }
//}

import com.projejo_academia_back.models.ExerciseTraining;
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
        this.idExerciseTraining = exerciseTraining.getId_exerciseTraining();
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

