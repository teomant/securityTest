package org.teomant.securityTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teomant.securityTest.entity.AuthoritiesEntity;

public interface AuthoritiesRepository extends JpaRepository<AuthoritiesEntity, Long> {

//    @Query( "select au.authority from AuthoritiesEntity au where au.user = :user" )
//    List<AuthoritiesEntity> getAuthoritiesByUser(
//            @Param( "user" )
//                    UserEntity user );
}
