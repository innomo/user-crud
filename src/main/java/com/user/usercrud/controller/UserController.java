package com.user.usercrud.controller;

import com.user.usercrud.model.User;
import com.user.usercrud.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        //User newUser =  userService.addUser(user);
        return  userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/{id}")
    public  void deleteuser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
