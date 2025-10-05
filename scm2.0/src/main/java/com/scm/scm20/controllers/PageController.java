package com.scm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
  @RequestMapping("/home")
  public String home(Model m){
    m.addAttribute("name", "Nishikant Dalal");
    m.addAttribute("city", "I live in Nagpur");
    m.addAttribute("link", "https://www.google.com/?zx=1759598438629&no_sw_cr=1");
    return "home";
  }
  @RequestMapping("/about")
  public String about(){
     return "about";
  }
  @RequestMapping("/service")
  public String service(){
   
     return "service";
  }

  @GetMapping("/contact")
  public String contact(){
    return new String("contact");
  }
  @GetMapping("/login")
  public String login(){
    return "login";
  }
  @GetMapping("/register")
  public String register(){
    return "register";
  }
} 
