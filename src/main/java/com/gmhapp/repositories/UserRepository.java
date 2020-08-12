package com.gmhapp.repositories;

import com.gmhapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.userName = :userName")
    UserEntity findByUserName(@Param("userName") String userName);

    @Query("SELECT case when count(u)> 0 then true else false end from UserEntity u where " +
            "lower(u.userName) like lower(:u_name)")
    boolean existsUserEntityByUserName(@Param("u_name") String u_name);

    @Query("SELECT case when count(u)> 0 then true else false end from UserEntity u where " +
            "lower(u.email) like lower(:email)")
    boolean existsUserEntityByEmail(@Param("email") String email);

    boolean existsUserEntityById(int id);

    int countAllByIdIsNotNull();


}
