package org.teomant.securityTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teomant.securityTest.entity.AuthoritiesEntity;
import org.teomant.securityTest.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query( "select ue.authorities from UserEntity ue where ue.id = :id" )
    List<AuthoritiesEntity> getAuthoritiesByUserId(
            @Param( "id" )
                    Long id );

    @Query( "select ue from UserEntity ue where ue.username = :username" )
    UserEntity getUserByUsername(
            @Param( "username" )
                    String username );
}
