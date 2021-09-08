package com.example.atm.task313.controller;

import com.example.atm.task313.model.User;
import com.example.atm.task313.service.RoleService;
import com.example.atm.task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("listRoles", roleService.findAllRoles());

        List<User> userList = userService.findAllUsers();
        model.addAttribute("listU", userList);

        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        return "admin-page";
    }




}
