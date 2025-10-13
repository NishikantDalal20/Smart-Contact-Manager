package com.scm.scm20.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
  
  @NotBlank(message="Name is required")
  @Size(min=3,max=30,message="Name must be between 3 to 30 characters")
  private String name;

  @NotBlank(message="Email is required")
  @Size(min=5,max=50,message="Email must be between 5 to 50 characters")
  private String email;

  @NotBlank(message="Password is required")
  @Size(min=5,max=50,message="Password must be between 5 to 50 characters")
  private String password;

  @NotBlank(message="Phone number is required")
  @Size(min=10,message="Phone number must be of 10 digits")
  private String phoneNumber;
  
  @NotBlank(message="About is required")
  @Size(min=10,max=500,message="About must be less than 500 characters and more than 10 characters")
  private String about;
}
