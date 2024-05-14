package com.example.diet.plan.Service;


import com.example.diet.plan.Exception.DietNotFoundException;
import com.example.diet.plan.Exception.UserNotFoundException;
import com.example.diet.plan.Model.DietPlan;
import com.example.diet.plan.Model.DietPlanGenerater;
import com.example.diet.plan.Model.User;
import com.example.diet.plan.Repository.DietPlanRepo;
import com.example.diet.plan.Repository.UserRepo;
import com.example.diet.plan.RequestDTOs.DietPlanDTO;
import com.example.diet.plan.RequestDTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietPlanService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DietPlanRepo dietPlanRepo;

    @Autowired
    private DietPlanGenerater dietPlanGenerater;
    public String addDiet(DietPlanDTO dietPlanDTO) {

        DietPlan dietPlan = DietPlan.builder()
                .startDate(dietPlanDTO.getStartDate())
                .endDate(dietPlanDTO.getEndDate()).build();

        Optional<User> optionalUser = userRepo.findById(dietPlanDTO.getUserId());
        User user = optionalUser.get();

        dietPlan.setUser(user);

        user.getDietPlanList().add(dietPlan);

        dietPlanRepo.save(dietPlan);
        return "Diet Plan has been saved successfully!";
    }

    public DietPlan getByid(Integer id) {
        Optional<DietPlan> optionalDietPlan = dietPlanRepo.findById(id);
        if(optionalDietPlan.isPresent())
        {
            return optionalDietPlan.get();
        }
        else{
            throw new DietNotFoundException("There is not diet with the entered id: "+ id);
        }
    }

    public List<DietPlan> getAll() {
        List<DietPlan> list = dietPlanRepo.findAll();
        return list;
    }

    public DietPlan update(Integer id, DietPlanDTO dietPlanDTO) {

        Optional<DietPlan> optionalDietPlan = dietPlanRepo.findById(id);
        if(optionalDietPlan.isPresent()){
            DietPlan dietPlan = optionalDietPlan.get();
            dietPlan.setStartDate(dietPlanDTO.getStartDate());
            dietPlan.setEndDate(dietPlanDTO.getEndDate());

            dietPlanRepo.save(dietPlan);
            return dietPlan;
        }
        else{
            throw new DietNotFoundException("There is not diet with the entered id: "+ id);
        }
    }

    public String delete(Integer id) {

        Optional<DietPlan> optionalDietPlan = dietPlanRepo.findById(id);
        if(optionalDietPlan.isPresent()){
            dietPlanRepo.deleteById(id);
            return "Diet - plan has been removed!";
        }
        else{
            throw new DietNotFoundException("There is not diet with the entered id: "+ id);
        }
    }

    public DietPlan suggestPlan(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            DietPlan dietPlan = dietPlanGenerater.generatePlan(user1);
            user1.getDietPlanList().add(dietPlan);
            dietPlanRepo.save(dietPlan);
            return dietPlan;
        }
        else{
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }
    }
}
