package com.example.diet.plan.Model;

import com.example.diet.plan.Enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String name;

    private String phone;

    @Column(unique = true)
    private String email;

    private Integer age;

    private Double weight;

    private Double height;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<DietPlan> dietPlanList = new ArrayList<>();

}
