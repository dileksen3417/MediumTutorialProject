package com.dsen.upcycling.service;

import com.dsen.upcycling.bean.dtos.UserDTO;
import com.dsen.upcycling.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUser(Long id);

    List<User> getAllUsers();

    User addUser(UserDTO userDto);

    User updateUser(UserDTO userDto, Long userId);

    void deleteUser(Long id);

}
