package com.example.diet.plan.Service;

import com.example.diet.plan.Exception.UserNotFoundException;
import com.example.diet.plan.Model.User;
import com.example.diet.plan.Repository.UserRepo;
import com.example.diet.plan.RequestDTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public void addUser(UserDTO userDTO) {

        User user = User.builder().age(userDTO.getAge())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .height(userDTO.getHeight())
                .weight(userDTO.getWeight())
                .gender(userDTO.getGender()).build();

        userRepo.save(user);
    }

    public List<User> getallUsers() {
        List<User> list = userRepo.findAll();
        return list;
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }
    }

    public User updateUser(Integer id, UserDTO userDTO) {

        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setAge(userDTO.getAge());
            user.setEmail(userDTO.getEmail());
            user.setHeight(userDTO.getHeight());
            user.setGender(userDTO.getGender());
            user.setPhone(userDTO.getPhone());
            user.setWeight(userDTO.getWeight());
            user.setName(userDTO.getName());

            userRepo.save(user);
            return user;
        }
        else{
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }
    }

    public String deleteById(Integer id) {

        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){

            userRepo.deleteById(id);
            return "User has been deleted Successfully!";
        }
        else{
            throw new UserNotFoundException("There is no existing user with id "+ id);
        }
    }
}
