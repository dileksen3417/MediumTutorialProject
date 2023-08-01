package com.dsen.upcycling.service.impl;

import com.dsen.upcycling.bean.dtos.UserDTO;
import com.dsen.upcycling.bean.enums.RowStatus;
import com.dsen.upcycling.entity.User;
import com.dsen.upcycling.repository.UserRepo;
import com.dsen.upcycling.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(UserDTO userDto) {
        return userRepo.save(convertUserDtoToUser(userDto));
    }

    private User convertUserDtoToUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    @Override
    public User updateUser(UserDTO userDto, Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            throw new EntityNotFoundException("User with id = " + userId + " not found!") ;
        }

        User toUpdate = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        return updateUserFromDto(userDto, toUpdate);
    }

    private User updateUserFromDto(UserDTO userDTO, User toUpdate) {
        if (Objects.nonNull(userDTO.getFirstname()) && !"".equalsIgnoreCase(userDTO.getFirstname())) {
            toUpdate.setFirstname(userDTO.getFirstname());
        }
        if (Objects.nonNull(userDTO.getLastname()) && !"".equalsIgnoreCase(userDTO.getLastname())) {
            toUpdate.setLastname(userDTO.getLastname());
        }
        if (Objects.nonNull(userDTO.getEmail()) && !"".equalsIgnoreCase(userDTO.getEmail())) {
            toUpdate.setEmail(userDTO.getEmail());
        }
        if (Objects.nonNull(userDTO.getPhoneNumber()) && !"".equalsIgnoreCase(userDTO.getPhoneNumber())) {
            toUpdate.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (Objects.nonNull(userDTO.getIdentityNumber()) && !"".equalsIgnoreCase(userDTO.getIdentityNumber())) {
            toUpdate.setIdentityNumber(userDTO.getIdentityNumber());
        }
        return userRepo.save(toUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        User toDelete = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toDelete.setRowStatus(RowStatus.DELETED);
        userRepo.save(toDelete);
    }
}
