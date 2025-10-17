package com.scm.scm20.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm20.entites.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updatUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String id);

    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);
}
