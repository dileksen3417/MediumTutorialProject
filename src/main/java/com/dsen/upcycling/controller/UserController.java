package com.dsen.upcycling.controller;

import com.dsen.upcycling.bean.dtos.UserDTO;
import com.dsen.upcycling.entity.User;
import com.dsen.upcycling.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long userId)
    {
        return userService.updateUser(userDTO, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long userId)
    {
        userService.deleteUser(userId);
        return "Deleted Successfully";
    }

}
