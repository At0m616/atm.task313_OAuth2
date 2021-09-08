package com.example.atm.task313.controller;

import com.example.atm.task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;


@Controller
public class UsersController {
    private final UserService userService;


    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userData(Principal principal, Model model) {
        var user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user-info";
    }

}
