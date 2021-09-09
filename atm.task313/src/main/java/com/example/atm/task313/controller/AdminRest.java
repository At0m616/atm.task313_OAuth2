package com.example.atm.task313.controller;

import com.example.atm.task313.dto.UserSimpleDto;
import com.example.atm.task313.model.Role;
import com.example.atm.task313.model.User;
import com.example.atm.task313.service.RoleService;
import com.example.atm.task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRest {
    private final UserService userService;

    private final RoleService roleService;


    @Autowired
    public AdminRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> userList = userService.findAllUsers();
        userList.sort(Comparator.comparing(User::getUsername));
        return userList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        if(userId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var user = userService.findUserById(userId);
        return user == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/im")
    public ResponseEntity<User> userData(Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        return user == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody UserSimpleDto userDto) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            roleSet.add(roleService.findByName(roleName));
        }
        var user = new User(userDto);
        user.setRoles(roleSet);
        userService.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UserSimpleDto userDto) {
        var user = new User(userDto);
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            roleSet.add(roleService.findByName(roleName));
        }
        user.setRoles(roleSet);
        user.setId(userDto.getId());
        userService.updateUser(user);
        var updatedUser = userService.findUserById(user.getId());

        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> removeUser(@PathVariable Long userId) {
        if(userId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var user = userService.findUserById(userId);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
