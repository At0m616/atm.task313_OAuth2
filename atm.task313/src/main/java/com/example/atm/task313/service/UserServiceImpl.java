package com.example.atm.task313.service;


import com.example.atm.task313.dao.UserDao;
import com.example.atm.task313.model.Role;
import com.example.atm.task313.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void addDefaultUserAndRolesIfNotExist() {
        if ((roleService.findById(1L) == null)
                || (roleService.findById(2L) == null)) {
            roleService.save(new Role(1L, "ROLE_ADMIN"));
            roleService.save(new Role(2L, "ROLE_USER"));
        }
        if (userDao.findDistinctByUsername("t@t") == null) {
            var userAdmin = new User();
            userAdmin.setUsername("t@t");
            userAdmin.setPassword(bCryptPasswordEncoder.encode("qqq"));
            userAdmin.setFirstname("admin");
            userAdmin.setLastname("admin");
            userAdmin.setAge(11);
            List<String> role = Collections.singletonList("ROLE_ADMIN");
            userAdmin.setRoles(roleService.findRolesSetByName(role));
            userDao.save(userAdmin);
        }

    }

    @Transactional
    @Override
    public void saveUser(User user) {

        if (userDao.findDistinctByUsername(user.getUsername()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
        } else {
            try {
                throw new Exception("User exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getId() == null) {
            try {
                throw new Exception("User not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!user.getPassword().equals("") || user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String name) {
        var userFindDB = userDao.findDistinctByUsername(name);
        if (userFindDB == null) {
            throw new UsernameNotFoundException("User not exist");
        }
        return userFindDB;
    }


    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userDao.findAll(Sort.by(Sort.Direction.ASC, "firstname"));
    }

}
