package org.teomant.securityTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.teomant.securityTest.entity.UserEntity;
import org.teomant.securityTest.service.UserService;

@RestController
public class TestRestController {

    @Autowired
    UserService userService;

    @RequestMapping("/apitest")
    @ResponseBody
    public UserEntity welcome() {
        UserEntity userEntity = userService.getUserById(1L).get();
        userEntity.setAuthorities(userService.getAutoritiesByUserId(userEntity.getId()));
        System.out.println(userEntity);
        return userEntity;
    }
}
