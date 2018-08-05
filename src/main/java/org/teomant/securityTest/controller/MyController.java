package org.teomant.securityTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.teomant.securityTest.entity.UserEntity;
import org.teomant.securityTest.service.AuthoritiesService;
import org.teomant.securityTest.service.UserService;

import java.security.Principal;

@Controller
public class MyController {

//    @Autowired
//    private AuthoritiesService authoritiesService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/")
//    public String index(Model model) {
////        model.addAttribute("message", "You are logged in as " + principal.getName());
//        UserEntity userEntity = userService.getUserById(1L).get();
//        userEntity.setAuthorities(userService.getAutoritiesByUserId(1L));
//        System.out.println(userEntity.getUsername());
//        System.out.println(userEntity.getAuthorities().size());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode(userEntity.getPassword()));
//        model.addAttribute("userEntity", userEntity);
//        return "index";
//    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal==null){
            return "redirect: /login";
        }
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }

    @GetMapping("/admin/page")
    public String adminPage(Model model, Principal principal) {
        return "adminPage";
    }
    @GetMapping("/user/page")
    public String userPage(Model model, Principal principal) {
        return "userPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}