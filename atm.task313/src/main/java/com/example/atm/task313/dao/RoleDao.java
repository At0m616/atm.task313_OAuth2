package com.example.atm.task313.dao;


import com.example.atm.task313.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findRolesByName(String name);

    Set<Role> findRolesByNameIn(List<String> names);
}
