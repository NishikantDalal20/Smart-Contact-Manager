package com.scm.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm20.entites.User;

public interface userRepo extends JpaRepository<User, String>{
    //custom finder method
    Optional<User> findByEmail(String email);
}
