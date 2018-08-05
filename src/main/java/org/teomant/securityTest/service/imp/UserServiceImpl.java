package org.teomant.securityTest.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teomant.securityTest.entity.AuthoritiesEntity;
import org.teomant.securityTest.entity.UserEntity;
import org.teomant.securityTest.repository.UserRepository;
import org.teomant.securityTest.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<AuthoritiesEntity> getAutoritiesByUserId(Long id) {
        return userRepository.getAuthoritiesByUserId(id);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
