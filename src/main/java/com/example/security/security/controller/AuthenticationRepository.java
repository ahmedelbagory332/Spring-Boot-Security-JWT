package com.example.security.security.controller;


 import com.example.security.security.model.CreateUser;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;

 import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<CreateUser, Long> {
 Optional<CreateUser> findByEmail(@Param("email") String email);

}
