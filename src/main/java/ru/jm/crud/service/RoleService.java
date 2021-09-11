package ru.jm.crud.service;

import ru.jm.crud.model.UserRole;

import java.util.ArrayList;


public interface RoleService {

    String add(UserRole userRole);
    UserRole getRole(Integer id);
    UserRole getRole(String role);
    ArrayList<UserRole> getRoles();

}