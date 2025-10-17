package com.scm.scm20.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {

    private Dotenv dotenv;

    @PostConstruct
    public void loadEnv() {
        // Load from project root safely; ignore if missing
        dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // ensures project root
                .ignoreIfMissing()                         // avoids exceptions if .env missing
                .load();

        // Set system properties
        if(dotenv.get("GOOGLE_CLIENT_ID") != null) {
            System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        }
        if(dotenv.get("GOOGLE_CLIENT_SECRET") != null) {
            System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
        }
        if(dotenv.get("GITHUB_CLIENT_ID") != null) {
            System.setProperty("GITHUB_CLIENT_ID", dotenv.get("GITHUB_CLIENT_ID"));
        }
        if(dotenv.get("GITHUB_CLIENT_SECRET") != null) {
            System.setProperty("GITHUB_CLIENT_SECRET", dotenv.get("GITHUB_CLIENT_SECRET"));
        }

        // Debug print
        System.out.println("✅ Loaded GOOGLE_CLIENT_ID: " + dotenv.get("GOOGLE_CLIENT_ID"));
        System.out.println("✅ Loaded GITHUB_CLIENT_ID: " + dotenv.get("GITHUB_CLIENT_ID"));
    }
}
