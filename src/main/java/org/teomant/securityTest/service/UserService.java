package org.teomant.securityTest.service;

import org.teomant.securityTest.entity.AuthoritiesEntity;
import org.teomant.securityTest.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getUserById(Long id);
    List<AuthoritiesEntity> getAutoritiesByUserId(Long id);
    UserEntity findUserByUsername(String username);
    UserEntity save(UserEntity userEntity);

}
