package com.develop.jina1.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomUserDetailsSecurityContextFactory.class)
public @interface WithCustomUserDetails {
    String username() default "testUser";
}
