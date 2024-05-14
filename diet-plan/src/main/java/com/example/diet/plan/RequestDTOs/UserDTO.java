package com.example.diet.plan.RequestDTOs;

import com.example.diet.plan.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;

    private String phone;

    @Column(unique = true)
    private String email;

    private Integer age;

    private Double weight;

    private Double height;

    private Gender gender;
}
