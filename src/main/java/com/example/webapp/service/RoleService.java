package com.example.webapp.service;

import com.example.webapp.models.Role;
import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role findById(Long id);

    void saveRole(Role role);
}
