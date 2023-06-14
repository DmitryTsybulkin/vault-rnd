package com.example.appwithvault.services;

public interface EncryptDecrypt {

    String encrypt(String sensitiveData);
    String decrypt(String hash);

}
