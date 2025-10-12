package com.scm.scm20.entites;

import java.util.ArrayList;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private int userId;

  @Column(name="user_name",nullable = false)
  private String name;

  @Column(unique = true,nullable=false)
  private String email;

  private String password;
  private String about;
  private String profilePic;
  private String phoneNumber;

  private boolean enabled=false;
  private boolean emailVerified=false;
  private boolean phoneVerified=false;

  private Providers provider=Providers.SELF;
  private String providersId;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Contact> contacts = new ArrayList<>();
}
