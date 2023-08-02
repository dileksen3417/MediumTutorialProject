package com.dsen.upcycling.controller;

import com.dsen.upcycling.bean.dtos.UserDTO;
import com.dsen.upcycling.entity.User;
import com.dsen.upcycling.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "User Management")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(hidden = true))))
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(summary = "Add an User", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")})
    @Parameter(name = "X-Auth-Token", description = "Token got after login", required = true, in = ParameterIn.HEADER)
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
    @Hidden
    public String deleteUserById(@PathVariable("id") Long userId)
    {
        userService.deleteUser(userId);
        return "Deleted Successfully";
    }

}
