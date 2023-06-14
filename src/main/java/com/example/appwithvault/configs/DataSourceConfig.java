package com.example.appwithvault.configs;

import com.example.appwithvault.services.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final SecretService secretService;

    @Autowired
    public DataSourceConfig(@Qualifier("secretServiceWithTemplate") SecretService secretService) {
        this.secretService = secretService;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        Map<String, Object> dbData = secretService.getSecretData("secret", "db-creds");
        managerDataSource.setDriverClassName((String) dbData.get("driverClass"));
        managerDataSource.setUrl((String) dbData.get("url"));
        managerDataSource.setUsername((String) dbData.get("username"));
        managerDataSource.setPassword((String) dbData.get("password"));
        managerDataSource.setSchema((String) dbData.get("schema"));
        return managerDataSource;
    }

}
