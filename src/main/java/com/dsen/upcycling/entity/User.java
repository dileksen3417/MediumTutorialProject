package com.dsen.upcycling.entity;

import com.dsen.upcycling.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @JsonIgnore
    @Column(name = "USER_NAME")
    private @Getter @Setter String username;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private @Getter @Setter String password;

    @Column(name = "FIRST_NAME")
    private @Getter @Setter String firstname;

    @Column(name = "LAST_NAME")
    private @Getter @Setter String lastname;

    @Column(name = "EMAIL")
    private @Getter @Setter String email;

    @Column(name = "PHONE_NUMBER")
    private @Getter @Setter String phoneNumber;

    @Column(name = "IDENTITY_NUMBER")
    private @Getter @Setter String identityNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

