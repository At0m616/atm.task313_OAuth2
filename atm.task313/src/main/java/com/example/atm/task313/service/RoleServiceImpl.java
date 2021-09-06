package com.example.atm.task313.service;

import com.example.atm.task313.dao.RoleDao;
import com.example.atm.task313.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional
    @Override
    public Role findById(Long id) {
        Role role = null;
        Optional<Role> optional = roleDao.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Transactional
    @Override
    public Role findByName(String name) {
        return roleDao.findRolesByName(name);
    }

    @Transactional
    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public Set<Role> findRolesSetById(Long[] id) {
        return roleDao.findRolesSetById(id);
    }

    @Override
    public Set<Role> findRolesSetByName(String[] names) {
        return roleDao.findRoleSetByName(names);
    }
}
