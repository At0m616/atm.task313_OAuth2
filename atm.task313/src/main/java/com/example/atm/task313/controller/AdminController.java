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
//        userList.sort(Comparator.comparing(User::getUsername));
        model.addAttribute("listU", userList);

        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        return "admin-page";
    }

//
//    @PostMapping("/user")
//    public String createNewUser(@ModelAttribute("user") @Valid User userForm, BindingResult bindingResult,
//                                @RequestParam(required = false, name = "roles") Long[] rolesId) {
//        if (bindingResult.hasErrors()) {
//            return "admin-page";
//        }
//        userService.saveUser(userForm);
//
//        return "redirect:/admin";
//    }
//
//
//    @PatchMapping("/{userId}")
//    public String updateUser(@PathVariable("userId") Long id, @ModelAttribute("user") User user,
//                             @RequestParam(required = false, name = "roles") Long[] roles) {
//        userService.updateUser(user);
//
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/delete/{userId}")
//    public String removeUser(@PathVariable("userId") Long id) {
//        userService.removeUser(id);
//        return "redirect:/admin";
//    }


}
