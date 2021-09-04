package com.example.webapp.dao;

import com.example.webapp.models.Role;
import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    Role findById(Long id);

    void saveRole(Role role);
}
