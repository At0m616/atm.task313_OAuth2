package com.example.atm.task313.controller;

import com.example.atm.task313.model.User;
import com.example.atm.task313.service.RoleService;
import com.example.atm.task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UsersController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String userData(Authentication authentication, Model model) {

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            var user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);

        } else if (authentication instanceof OAuth2AuthenticationToken) {
            DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
            String email = oauthUser.getEmail();

            var userOauth = userService.findUserByEmail(email);

            if (userOauth == null) {
                var newOauthUser = new User();
                newOauthUser.setFirstname(oauthUser.getGivenName());
                newOauthUser.setLastname(oauthUser.getFamilyName());
                newOauthUser.setUsername(oauthUser.getEmail());
                newOauthUser.setPassword(passwordEncoder.encode(oauthUser.getAccessTokenHash()));

                List<String> role = Collections.singletonList("ROLE_USER");
                newOauthUser.setRoles(roleService.findRolesSetByName(role));

                userService.saveUser(newOauthUser);
                userOauth = userService.findUserByEmail(email);
            }
            model.addAttribute("user", userOauth);
        }

        return "user-info";
    }


}