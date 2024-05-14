package com.example.diet.plan.Service;

import com.example.diet.plan.Exception.FoodItemNotFound;
import com.example.diet.plan.Model.Food;
import com.example.diet.plan.Model.Meals;
import com.example.diet.plan.Repository.FoodRepo;
import com.example.diet.plan.Repository.MealsRepo;
import com.example.diet.plan.RequestDTOs.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private MealsRepo mealsRepo;

    @Autowired
    private FoodRepo foodRepo;
    public String addFood(FoodDTO foodDTO) {

        Food food = Food.builder()
                .protein(foodDTO.getProtein())
                .name(foodDTO.getName())
                .calories(foodDTO.getCalories()).build();

        Optional<Meals> optionalMeals  = mealsRepo.findById(foodDTO.getMealId());
        Meals meals = optionalMeals.get();

        if(meals != null){
            food.setMeals(meals);
            meals.getFoodList().add(food);
        }

        foodRepo.save(food);
        return "Food Has Been Added!";
    }

    public Food getByid(Integer id) {
        
        Optional<Food> optionalFood = foodRepo.findById(id);
       if(optionalFood.isPresent()){
           return optionalFood.get();
       }
       else {
           throw new FoodItemNotFound("There is no food item with id: "+ id);
       }
    }

    public List<Food> getAll() {
        List<Food> list = foodRepo.findAll();
        return list;
    }

    public Food update(Integer id, FoodDTO foodDTO) {

        Optional<Food> optionalFood = foodRepo.findById(id);
        if(optionalFood.isPresent()){
            Food food = optionalFood.get();
            food.setName(foodDTO.getName());
            food.setProtein(foodDTO.getProtein());
            food.setCalories(foodDTO.getCalories());
            foodRepo.save(food);
            return food;
        }
        else{
            throw new FoodItemNotFound("There is no food item with id: "+ id);
        }
    }

    public String deleteByid(Integer id) {

        Optional<Food> foodOptional = foodRepo.findById(id);
        if(foodOptional.isPresent()){
            foodRepo.deleteById(id);
            return "Food item has been deleted successfully!";
        }
        else{
            throw new FoodItemNotFound("There is no food item with id: "+ id);
        }
    }
}
