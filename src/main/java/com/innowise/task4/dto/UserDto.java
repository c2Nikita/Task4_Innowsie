package com.innowise.task4.dto;

import com.innowise.task4.model.UserRole;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private UserRole userRole;

    public UserDto(Long id, String name, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.userRole = userRole;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && userRole == userDto.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userRole);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
