package com.scm.scm20.config;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm20.services.impl.SecurityCustomUserDetailService;



@Configuration
public class SecurityConfig {
//    @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails user = User.withUsername("nishikant")
//                 .password("{noop}nishikant") // no password encoding
//                 .roles("USER")
//                 .build();

//         UserDetails admin = User.withUsername("admin")
//                 .password("{noop}admin")
//                 .roles("ADMIN")
//                 .build();

//         return new InMemoryUserDetailsManager(user, admin);
//     }


@Autowired
private SecurityCustomUserDetailService userDetailsService;


@Autowired
private OAuthAuthenicationSuccessHandler  oAuthAuthenicationSuccessHandler;

@Bean
public AuthenticationProvider authenticationProvider(){
   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   authProvider.setUserDetailsService(userDetailsService);
   authProvider.setPasswordEncoder(passwordEncoder());
   return authProvider;
}

@Bean
public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/**").authenticated() // Only authenticated users
            .anyRequest().permitAll() // Other URLs open
        )
        .formLogin(form -> form
            .loginPage("/login") // Custom login page
            .loginProcessingUrl("/authenticate") // Form action URL
            .successForwardUrl("/user/dashboard")
            //.failureForwardUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password")
            
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
  
            
        )
        .csrf(csrf -> csrf
            .disable() // Disable CSRF for simplicity (not recommended for production)
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/login") // Custom login page for OAuth2
            .successHandler(oAuthAuthenicationSuccessHandler) // Redirect after successful OAuth2 login
        );

    return http.build();
}



}
