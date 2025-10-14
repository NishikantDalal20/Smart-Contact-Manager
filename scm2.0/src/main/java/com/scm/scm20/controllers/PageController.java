package com.scm.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.entites.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.helpers.Message;
import com.scm.scm20.helpers.MessageType;
import com.scm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

  @Autowired
  UserService userService;

  @RequestMapping("/home")
  public String home(Model m) {
    m.addAttribute("name", "Nishikant Dalal");
    m.addAttribute("city", "I live in Nagpur");
    m.addAttribute("link", "https://www.google.com/?zx=1759598438629&no_sw_cr=1");
    return "home";
  }

  @RequestMapping("/")
  public String defaultUrl() {
    return "home";
  }

  @RequestMapping("/about")
  public String about() {
    return "about";
  }

  @RequestMapping("/service")
  public String service() {

    return "service";
  }

  @GetMapping("/contact")
  public String contact() {
    return new String("contact");
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    UserForm userForm = new UserForm();
    model.addAttribute("userForm", userForm);
    return "register";
  }

  @PostMapping("/do-register")
  public String processRegister(@Valid @ModelAttribute UserForm userForm , BindingResult result,HttpSession session) {
    // fetch data

    // validate data
    if(result.hasErrors()){
      return "register";
    }

    // UserForm -> User
    // User user= User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .phoneNumber(userForm.getPhoneNumber())
    // .about(userForm.getAbout())
    // .build();

    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setAbout(userForm.getAbout());

    // save to db
    User savedUser = userService.saveUser(user);

    System.out.println(savedUser);

    Message message = Message.builder()
            .content("Registered Successfully !!")
            .type(MessageType.green)
            .build();

    session.setAttribute("message", message);


   

    return "redirect:/register";
  }
}
