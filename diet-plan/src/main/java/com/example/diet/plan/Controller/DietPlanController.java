package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.DietNotFoundException;
import com.example.diet.plan.Exception.UserNotFoundException;
import com.example.diet.plan.Model.DietPlan;
import com.example.diet.plan.RequestDTOs.DietPlanDTO;
import com.example.diet.plan.RequestDTOs.UserDTO;
import com.example.diet.plan.Service.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("dietplan")
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;
    @PostMapping("addDiet")
    public ResponseEntity<String> addDiet(@RequestBody DietPlanDTO dietPlanDTO){

        String response = dietPlanService.addDiet(dietPlanDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getbyId/{id}")
    public ResponseEntity getByid(@PathVariable Integer id) throws Exception{

        try {
            DietPlan dietPlan = dietPlanService.getByid(id);
            return new ResponseEntity(dietPlan,HttpStatus.FOUND);
        }
        catch (DietNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<DietPlan>> getAll(){
        List<DietPlan> list = dietPlanService.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody DietPlanDTO dietPlanDTO) throws Exception{
        try{
            DietPlan dietPlan = dietPlanService.update(id,dietPlanDTO);
            return new ResponseEntity(dietPlan,HttpStatus.OK);
        }
        catch(DietNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception{
        try {
            String response = dietPlanService.delete(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (DietNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("suggest-plan/{id}")
    public ResponseEntity suggestPlan(@PathVariable Integer id) throws Exception{

        try{
            DietPlan dietPlan = dietPlanService.suggestPlan(id);
            return new ResponseEntity<>(dietPlan,HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
