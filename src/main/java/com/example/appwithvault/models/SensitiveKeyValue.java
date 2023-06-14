package com.example.appwithvault.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class SensitiveKeyValue implements Sensitive {

    private Map<String, Object> keyValueStorage;

    public SensitiveKeyValue(Map<String, Object> keyValueStorage) {
        this.keyValueStorage = keyValueStorage;
    }
}
