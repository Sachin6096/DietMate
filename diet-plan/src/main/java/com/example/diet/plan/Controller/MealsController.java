package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.MealsNotFoundException;
import com.example.diet.plan.Model.Meals;
import com.example.diet.plan.RequestDTOs.MealsDTO;
import com.example.diet.plan.Service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meals")
public class MealsController {

    @Autowired
    private MealsService mealsService;

    @PostMapping("addMeal")
    public ResponseEntity addMeal(@RequestBody MealsDTO mealsDTO){
        String response = mealsService.addMeal(mealsDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Meals>> getAll(){
        List<Meals> list = mealsService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("byid/{id}")
    public ResponseEntity getById(@PathVariable Integer id) throws Exception{
        try {
            Meals meals = mealsService.getById(id);
            return new ResponseEntity<>(meals,HttpStatus.FOUND);
        }
        catch (MealsNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody MealsDTO mealsDTO) throws Exception{
        try {
            Meals meals = mealsService.update(id,mealsDTO);
            return new ResponseEntity<>(meals,HttpStatus.OK);
        }
        catch (MealsNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception{
       try{
           String response = mealsService.delete(id);
           return new ResponseEntity(response,HttpStatus.FOUND);
       }
       catch (MealsNotFoundException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
}
