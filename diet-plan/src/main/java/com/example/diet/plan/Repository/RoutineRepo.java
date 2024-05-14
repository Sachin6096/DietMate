package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepo extends JpaRepository<Routine,Integer> {
}
