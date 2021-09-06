package com.example.atm.task313.dao;


import com.example.atm.task313.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role findRolesByName(String name);

    default Set<Role> findRolesSetById(Long[] id) {
        Set<Role> roleSet = new HashSet<>();
        for (Long i : id) {
            roleSet.add(findById(i).orElse(null));
        }
        return roleSet;
    }

    default Set<Role> findRoleSetByName(String[] names) {
        Set<Role> roleSet = new HashSet<>();
        for (String s : names) {
            roleSet.add(findRolesByName(s));
        }
        return roleSet;
    }

}
