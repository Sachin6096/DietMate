package com.example.diet.plan.Repository;

import com.example.diet.plan.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
