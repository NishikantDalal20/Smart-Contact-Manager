package com.scm.scm20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm20.entites.User;
import com.scm.scm20.helpers.AppConstants;
import com.scm.scm20.helpers.ResourceNotFoundException;
import com.scm.scm20.repositories.userRepo;
import com.scm.scm20.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private userRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        
        //userId genereate dynamically
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);

        //password encoding
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {
       User user2=userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setPhoneNumber(user.getPhoneNumber());
       user2.setAbout(user.getAbout());
    //    user2.setAbout(user.getAbout());
    //    user2.setAbout(user.getAbout());
    //    user2.setAbout(user.getAbout());
    //    user2.setAbout(user.getAbout());
    //    user2.setAbout(user.getAbout());


    User user3=userRepo.save(user2);
    return Optional.ofNullable(user3);
    }

    @Override
    public void deleteUser(String id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String id) {
          User user=userRepo.findById(id).orElse(null);
          return user!=null ? true : false;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        return user!=null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
  
}
