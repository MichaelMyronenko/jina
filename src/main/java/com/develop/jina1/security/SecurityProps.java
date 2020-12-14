package com.develop.jina1.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ConfigurationProperties("application.security")
@Getter
@Setter
@Component
public class SecurityProps {
    private String signature;
    private Duration lifetime;
}
