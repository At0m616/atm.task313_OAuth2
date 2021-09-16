package com.example.atm.task313.service;



import com.example.atm.task313.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface RoleService {

    void save(Role role);

    Role findById(Long id);

    Role findByName(String name);

    Set<Role> findRolesSetByName(List<String> names);

    List<Role> findAllRoles();
}
