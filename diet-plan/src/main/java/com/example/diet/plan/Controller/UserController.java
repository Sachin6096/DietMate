package com.example.diet.plan.Controller;

import com.example.diet.plan.Exception.UserNotFoundException;
import com.example.diet.plan.Model.User;
import com.example.diet.plan.RequestDTOs.UserDTO;
import com.example.diet.plan.Service.UserService;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        String response = "User has been successfully added!";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getallUSers(){

        List<User> list = userService.getallUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("userById/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id)throws Exception{
        try{
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateuser/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) throws Exception{
        try {
            User user = userService.updateUser(id,userDTO);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws Exception{

        try {
            String response = userService.deleteById(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
