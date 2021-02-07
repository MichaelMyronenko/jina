package com.develop.jina1.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class JdbcProps {
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public JdbcProps(@Value("${spring.datasource.driver-class-name}") String jdbcDriver,
                     @Value("${spring.datasource.url}") String url,
                     @Value("${spring.datasource.username}") String username,
                     @Value("${spring.datasource.password}") String password) {
        this.jdbcDriver = jdbcDriver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
