package com.example.appwithvault.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SecretServiceWithTemplate implements SecretService {

    private final VaultTemplate vaultTemplate;

    @Override
    public Map<String, Object> getSecretData(String path, String identity) {
        VaultResponse response = vaultTemplate
                .opsForKeyValue(path, VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get(identity);
        return response.getData();
    }

    @Override
    public void putSecret(String path, Object data) {
        vaultTemplate.write(path, data);
    }

}
