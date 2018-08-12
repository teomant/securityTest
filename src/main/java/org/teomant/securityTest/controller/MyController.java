package org.teomant.securityTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.teomant.securityTest.controller.form.RegistrationForm;
import org.teomant.securityTest.entity.AuthoritiesEntity;
import org.teomant.securityTest.entity.UserEntity;
import org.teomant.securityTest.service.AuthoritiesService;
import org.teomant.securityTest.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collections;

@Controller
public class MyController {

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private UserService userService;

    @ModelAttribute( "registrationForm" )
    public RegistrationForm registrationForm(){
        return new RegistrationForm();
    }
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
//        if (principal==null){
//            return "redirect: /login";
//        }
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistration(Model model,
                                   HttpServletResponse response,
                                   @ModelAttribute( "registrationForm" )
                                           RegistrationForm registrationForm) {

        UserEntity user = userService.findUserByUsername(registrationForm.getUsername());
        if (user!=null){
            return "redirect:/registration";
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registrationForm.getUsername());
            userEntity.setPassword(new BCryptPasswordEncoder().encode(registrationForm.getPassword()));
            AuthoritiesEntity authoritiesEntity = new AuthoritiesEntity();
            authoritiesEntity.setAuthority(registrationForm.getRole());
            authoritiesEntity.setUser_id(userEntity);
            userService.save(userEntity);
            authoritiesService.save(authoritiesEntity);
            return "redirect:/";
        }
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