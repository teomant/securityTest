package org.teomant.securityTest.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teomant.securityTest.entity.AuthoritiesEntity;
import org.teomant.securityTest.repository.AuthoritiesRepository;
import org.teomant.securityTest.service.AuthoritiesService;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Override
    public AuthoritiesEntity save(AuthoritiesEntity authoritiesEntity) {
        return authoritiesRepository.save(authoritiesEntity);
    }
}
