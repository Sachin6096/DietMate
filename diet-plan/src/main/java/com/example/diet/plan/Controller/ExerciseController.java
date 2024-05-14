package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.ExerciseNotFoundException;
import com.example.diet.plan.Model.Exercise;
import com.example.diet.plan.RequestDTOs.ExerciseDTO;
import com.example.diet.plan.Service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;
    @PostMapping("addExercise")
    public ResponseEntity addExercise(@RequestBody ExerciseDTO exerciseDTO){
        String response = exerciseService.addExercise(exerciseDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("byid/{id}")
    public ResponseEntity getById(@PathVariable Integer id) throws Exception{

        try {
            Exercise exercise = exerciseService.getById(id);
            return new ResponseEntity<>(exercise,HttpStatus.OK);
        }
        catch (ExerciseNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Exercise>> getAll(){
        List<Exercise> list = exerciseService.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody ExerciseDTO exerciseDTO) throws Exception{

        try {
            Exercise exercise = exerciseService.update(id,exerciseDTO);
            return new ResponseEntity<>(exercise,HttpStatus.OK);
        }
        catch (ExerciseNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception{

        try {
            String response = exerciseService.delete(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (ExerciseNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
