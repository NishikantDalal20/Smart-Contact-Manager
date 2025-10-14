package com.scm.scm20.entites;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{
  @Id
  private String userId;

  @Column(name="user_name",nullable = false)
  private String name;

  @Column(unique = true,nullable=false)
  private String email;

  private String password;
  private String about;
  private String profilePic;
  private String phoneNumber;

  private boolean enabled=true;
  private boolean emailVerified=false;
  private boolean phoneVerified=false;

  @Enumerated(EnumType.STRING)
  private Providers provider=Providers.SELF;
  
  private String providersId;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Contact> contacts = new ArrayList<>();


@ElementCollection(fetch = FetchType.EAGER)
private List<String> roleList=new ArrayList<>();


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());

    return roles;
  }

  @Override
  public String getUsername() {
    return this.email;
  }


  @Override
  public boolean isEnabled(){
    return this.enabled;
  }


  @Override
  public String getPassword(){
    return this.password;
  }

  
}
