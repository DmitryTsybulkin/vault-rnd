package com.example.appwithvault.models;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("db-creds")
public class SimpleCredential implements Sensitive {

    private String username;
    private String password;

    public Map<String, Object> toMap() {
        return Map.of("username", username, "password", password);
    }

}
