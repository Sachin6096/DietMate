package com.example.diet.plan.Service;

import com.example.diet.plan.Exception.RoutineNotFoundException;
import com.example.diet.plan.Model.DietPlan;
import com.example.diet.plan.Model.Routine;
import com.example.diet.plan.Repository.DietPlanRepo;
import com.example.diet.plan.Repository.RoutineRepo;
import com.example.diet.plan.RequestDTOs.RoutineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    private DietPlanRepo dietPlanRepo;

    @Autowired
    private RoutineRepo routineRepo;
    public String addRoutine(RoutineDTO routineDTO) {

        Routine routine = Routine.builder()
                .weekDay(routineDTO.getWeekDay())
                .build();

        Optional<DietPlan> optionalDietPlan = dietPlanRepo.findById(routineDTO.getDietplanId());
        DietPlan dietPlan = optionalDietPlan.get();

        routine.setDietPlan(dietPlan);

        dietPlan.getRoutineList().add(routine);
        routineRepo.save(routine);

        return "Routine has been added to DB!";
    }

    public List<Routine> getAll() {
        List<Routine> list = routineRepo.findAll();
        return list;
    }

    public Routine getById(Integer id) {

        Optional<Routine> optionalRoutine = routineRepo.findById(id);
        if(optionalRoutine.isPresent()){
            return optionalRoutine.get();
        }
        else{
            throw new RoutineNotFoundException("Please enter a valid id");
        }
    }

    public Routine updateRoutine(Integer id, RoutineDTO routineDTO) {

        Optional<Routine> optionalRoutine = routineRepo.findById(id);
        if(optionalRoutine.isPresent()){
            Routine routine = optionalRoutine.get();
            routine.setWeekDay(routineDTO.getWeekDay());
            return routineRepo.save(routine);
        }
        else{
            throw new RoutineNotFoundException("Please enter a valid id");
        }
    }

    public String deleteByid(Integer id) {

        Optional<Routine> optionalRoutine = routineRepo.findById(id);
        if(optionalRoutine.isPresent()){
            routineRepo.deleteById(id);
            return "Routine has been deleted as per the request!";
        }
        else{
            throw new RoutineNotFoundException("Please enter a valid id");
        }
    }
}
