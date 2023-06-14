package com.example.appwithvault.services;

import java.util.Map;

public interface SecretService {

    Map<String, Object> getSecretData(String path, String identity);

    void putSecret(String path, Object data);

}
