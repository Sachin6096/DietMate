package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepo extends JpaRepository<DietPlan,Integer> {
}
