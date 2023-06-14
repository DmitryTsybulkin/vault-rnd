package com.example.appwithvault.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultSysOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.VaultMount;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class EncryptDecryptImpl implements EncryptDecrypt {

    @Value("${vault.private-key}")
    public String privateKey;
    private VaultTransitOperations transitOperations;
    private final VaultTemplate vaultTemplate;

    @PostConstruct
    public void init() {
        this.transitOperations = vaultTemplate.opsForTransit();
        VaultSysOperations sysOperations = vaultTemplate.opsForSys();
        if (!sysOperations.getMounts().containsKey("transit/")) {
            sysOperations.mount("transit", VaultMount.create("transit"));
            transitOperations.createKey(privateKey);
        }
    }

    @Override
    public String encrypt(String sensitiveData) {
        return transitOperations.encrypt(privateKey, sensitiveData);
    }

    @Override
    public String decrypt(String hash) {
        return transitOperations.decrypt(privateKey, hash);
    }
}
