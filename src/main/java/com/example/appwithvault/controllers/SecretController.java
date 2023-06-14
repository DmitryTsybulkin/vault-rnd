package com.example.appwithvault.controllers;

import com.example.appwithvault.models.Sensitive;
import com.example.appwithvault.services.EncryptDecrypt;
import com.example.appwithvault.services.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("secret")
public class SecretController {

    private final SecretService secretService;
    private final EncryptDecrypt encryptDecryptService;

    @Autowired
    public SecretController(@Qualifier("secretServiceWithTemplate") SecretService secretService,
                            EncryptDecrypt encryptDecryptService) {
        this.secretService = secretService;
        this.encryptDecryptService = encryptDecryptService;
    }

    @GetMapping
    public Object getSecret(@RequestParam("path") String path,
                                      @RequestParam("id") String id) {
        return secretService.getSecretData(path, id);
    }

    @PostMapping
    public void putSecret(@RequestParam("path") String path, @RequestParam("name") String name,
                          @RequestParam("id") String id, @RequestParam("value") String value) {
        Map<String, String> keyValue = Map.of(id, value);
        secretService.putSecret(path, keyValue);
    }

    @GetMapping(path = "/encrypt")
    public String encryptSecret(@RequestParam("secret") String secret) {
        return encryptDecryptService.encrypt(secret);
    }

    @GetMapping(path = "decrypt")
    public String decryptSecret(@RequestParam("hash") String hash) {
        return encryptDecryptService.encrypt(hash);
    }

}
