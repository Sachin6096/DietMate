package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise,Integer> {
}
