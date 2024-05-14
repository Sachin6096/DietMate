package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.FoodItemNotFound;
import com.example.diet.plan.Model.Food;
import com.example.diet.plan.RequestDTOs.FoodDTO;
import com.example.diet.plan.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("add")
    public ResponseEntity<String> addFood(@RequestBody FoodDTO foodDTO){

        String response = foodService.addFood(foodDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("getbyId/{id}")
    public ResponseEntity getByid(@PathVariable Integer id) throws Exception{

        try{
            Food food = foodService.getByid(id);
            return new ResponseEntity(food,HttpStatus.FOUND);
        }
        catch (FoodItemNotFound e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Food>> getAll(){

        List<Food> list= foodService.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody FoodDTO foodDTO) throws Exception{

        try{
            Food food = foodService.update(id,foodDTO);
            return new ResponseEntity<>(food,HttpStatus.FOUND);
        }
        catch(FoodItemNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable Integer id) throws Exception{
        try{
            String response = foodService.deleteByid(id);
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
        catch (FoodItemNotFound e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
