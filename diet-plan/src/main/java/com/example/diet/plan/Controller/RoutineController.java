package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.RoutineNotFoundException;
import com.example.diet.plan.Model.Routine;
import com.example.diet.plan.RequestDTOs.RoutineDTO;
import com.example.diet.plan.Service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping("addRoutine")
    public ResponseEntity addRoutine(@RequestBody RoutineDTO routineDTO){

        String response = routineService.addRoutine(routineDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Routine>> getAll(){

        List<Routine> list = routineService.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity getByid(@PathVariable Integer id) throws Exception{
        try{
            Routine routine = routineService.getById(id);
            return new ResponseEntity<>(routine,HttpStatus.FOUND);
        }
        catch (RoutineNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateRoutine(@PathVariable Integer id, @RequestBody RoutineDTO routineDTO) throws Exception{
        try{
            Routine routine = routineService.updateRoutine(id,routineDTO);
            return new ResponseEntity(routine,HttpStatus.OK);
        }
        catch (RoutineNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deletebyId(@PathVariable Integer id) throws Exception{

        try{
            String response = routineService.deleteByid(id);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (RoutineNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
