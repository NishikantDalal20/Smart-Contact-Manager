package com.scm.scm20.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
  public static String getEmailOfLoggedInUser(Principal principal) {
   



    if (principal instanceof Authentication) {

            Authentication authentication = (Authentication) principal;  // ✅ Proper cast

            Object principalObj = authentication.getPrincipal();  // ✅ Now valid

            if (principalObj instanceof OAuth2User oauthUser) {

                // ✅ Try email for Google / GitHub
                String email = oauthUser.getAttribute("email");
                if (email != null) return email;

                // ✅ GitHub users with private email
                String username = oauthUser.getAttribute("login");
                if (username != null) return username + "@github.com";

            }
        }

        return principal.getName(); // ✅ Fallback for normal form login
  }
}
