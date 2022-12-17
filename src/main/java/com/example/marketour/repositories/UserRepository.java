package com.example.marketour.repositories;

import com.example.marketour.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*@Query("SELECT u FROM User u WHERE u.userCredentials.username = (:username) AND u.userCredentials.password = (:password)")
    User findWithUsernameAndPassword(@Param("username") String username, @Param("password") String password);*/
}
