package com.oshovskii.spring.security.controllers;

import com.oshovskii.spring.security.entities.User;
import com.oshovskii.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Principal principal) {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        return "secured part of web service: " + principal.getName();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "read profile page";
    }


    @GetMapping("/only_for_admins/score/get/{id}")
    public String pageOnlyForAdmins(@PathVariable Long id) {
        return "admins page " + " user " + userService.findByUserId(1L);
    }


    @GetMapping("/authenticated/score/inc")
    public String incScorePage(Principal principal) {
        return "secured part of web service:"  + principal.getName() + " score: " + userService.userIncScore(principal.getName()) ;
    }

    @GetMapping("/authenticated/score/dec")
    public String decScorePage(Principal principal) {
        return "secured part of web service: " + principal.getName() + " score: " + userService.userDecScore(principal.getName());
    }

    @GetMapping("/authenticated/score/get/current")
    public String getCurrentScorePage(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "secured part of web service: " + " score: " + user.getScore();
    }
}
