package com.example.appwithvault.services;

import com.example.appwithvault.models.SimpleCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Service
@EnableConfigurationProperties(SimpleCredential.class)
public class SecretServiceWithProperties implements SecretService {

    private final SimpleCredential credentials;

    public SecretServiceWithProperties(SimpleCredential credentials) {
        this.credentials = credentials;
    }

    @PostConstruct
    public void init() {
        // todo: doesn't work!
        System.out.println("My credentials: " + credentials);
    }

    @Override
    public Map<String, Object> getSecretData(String path, String identity) {
        return credentials.toMap();
    }

    @Override
    public void putSecret(String path, Object data) {
        log.warn("Could not put value with config properties implementation");
    }
}
