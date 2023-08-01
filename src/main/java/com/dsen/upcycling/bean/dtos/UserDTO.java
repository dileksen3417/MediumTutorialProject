package com.dsen.upcycling.bean.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    private String username;

    @JsonIgnore
    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;

    private String identityNumber;
}
