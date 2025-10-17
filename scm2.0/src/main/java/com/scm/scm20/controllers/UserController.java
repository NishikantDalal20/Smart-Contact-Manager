package com.scm.scm20.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.entites.User;
import com.scm.scm20.helpers.Helper;
import com.scm.scm20.helpers.ResourceNotFoundException;
import com.scm.scm20.repositories.userRepo;
import com.scm.scm20.services.UserService;
import com.scm.scm20.services.impl.UserServiceImpl;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void loggedInfo(Model model,Principal principal){
        
        // if(principal==null){
        //     return;
        // }

        String email = Helper.getEmailOfLoggedInUser(principal);
        System.out.println("User email: " + email);

       User user=userService.getUserByEmail(email);

       model.addAttribute("user", user);
        System.out.println("User : " + user);
    }


    @RequestMapping("/dashboard")
    public String dashboard(){
        return "user/dashboard";
    }



    @RequestMapping("/profile")
    public String profile(){
       
        
        return "user/profile";
    }
}
