package com.example.diet.plan.Service;

import com.example.diet.plan.Exception.ExerciseNotFoundException;
import com.example.diet.plan.Exception.UserNotFoundException;
import com.example.diet.plan.Model.Exercise;
import com.example.diet.plan.Model.Routine;
import com.example.diet.plan.Repository.ExerciseRepo;
import com.example.diet.plan.Repository.RoutineRepo;
import com.example.diet.plan.RequestDTOs.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private RoutineRepo routineRepo;

    @Autowired
    private ExerciseRepo exerciseRepo;
    public String addExercise(ExerciseDTO exerciseDTO) {

        Exercise exercise = Exercise.builder()
                .name(exerciseDTO.getName())
                .duration(exerciseDTO.getDuration())
                .exerciseIntensity(exerciseDTO.getExerciseIntensity())
                .build();

        Optional<Routine> optionalRoutine = routineRepo.findById(exerciseDTO.getRoutineId());
        Routine routine = optionalRoutine.get();

        exercise.setRoutine(routine);

        routine.getExerciseList().add(exercise);

        exerciseRepo.save(exercise);
        return "Exercise has been added successfully!";
    }

    public Exercise getById(Integer id) {
        Optional<Exercise> optionalExercise = exerciseRepo.findById(id);
        if(optionalExercise.isPresent())
        {
            return optionalExercise.get();
        }
        else {
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }
    }

    public List<Exercise> getAll() {

        List<Exercise> list = exerciseRepo.findAll();
        return list;
    }

    public Exercise update(Integer id, ExerciseDTO exerciseDTO) {

        Optional<Exercise> optionalExercise = exerciseRepo.findById(id);

        if(optionalExercise.isPresent()){
                Exercise exercise = optionalExercise.get();
                exercise.setExerciseIntensity(exerciseDTO.getExerciseIntensity());
                exercise.setName(exerciseDTO.getName());
                exercise.setDuration(exerciseDTO.getDuration());
                exerciseRepo.save(exercise);
                return exercise;
        }
        else{
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }

    }

    public String delete(Integer id) {

        Optional<Exercise> optionalExercise = exerciseRepo.findById(id);
        if(optionalExercise.isPresent()){
            exerciseRepo.deleteById(id);
            return "Exercise has been removed from Database.";
        }
        else {
            throw new ExerciseNotFoundException("There is no exercise with given id" + id);
        }
    }
}
