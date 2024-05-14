package com.example.diet.plan.Service;

import com.example.diet.plan.Exception.MealsNotFoundException;
import com.example.diet.plan.Model.Meals;
import com.example.diet.plan.Model.Routine;
import com.example.diet.plan.Repository.MealsRepo;
import com.example.diet.plan.Repository.RoutineRepo;
import com.example.diet.plan.RequestDTOs.MealsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealsService {

    @Autowired
    private MealsRepo mealsRepo;

    @Autowired
    private RoutineRepo routineRepo;
    public String addMeal(MealsDTO mealsDTO) {

        Meals meals = Meals.builder()
                .mealType(mealsDTO.getMealType())
                .time(mealsDTO.getTime()).build();

        Optional<Routine> optionalRoutine = routineRepo.findById(mealsDTO.getRoutineId());
        Routine routine = optionalRoutine.get();

        meals.setRoutine(routine);

        routine.getMealsList().add(meals);

        mealsRepo.save(meals);
        return "Meal has been added successfully!";
    }

    public List<Meals> getAll() {

        List<Meals> list = mealsRepo.findAll();
        return list;
    }

    public Meals getById(Integer id) {

        Optional<Meals> optionalMeals = mealsRepo.findById(id);
        if(optionalMeals.isPresent()){
            return optionalMeals.get();
        }
        else {
            throw new MealsNotFoundException("Please enter a valid id");
        }
    }

    public Meals update(Integer id, MealsDTO mealsDTO) {

        Optional<Meals> optionalMeals = mealsRepo.findById(id);
        if(optionalMeals.isPresent()){
            Meals meals = optionalMeals.get();
            meals.setTime(mealsDTO.getTime());
            meals.setMealType(mealsDTO.getMealType());

            Optional<Routine> optionalRoutine = routineRepo.findById(mealsDTO.getRoutineId());
            if(optionalRoutine.isPresent()){
                meals.setRoutine(optionalRoutine.get());
            }

            mealsRepo.save(meals);
            return meals;
        }
        else{
            throw new MealsNotFoundException("Please enter a valid id");
        }
    }

    public String delete(Integer id) {
        Optional<Meals> optionalMeals = mealsRepo.findById(id);
        if(optionalMeals.isPresent()){
            mealsRepo.deleteById(id);
            return "Meal has been deleted!";
        }
        else {
            throw new MealsNotFoundException("Please enter a valid id");
        }
    }
}
