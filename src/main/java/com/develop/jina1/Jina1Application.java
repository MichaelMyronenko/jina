package com.develop.jina1;

import com.infobip.spring.data.jdbc.EnableQuerydslJdbcRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableQuerydslJdbcRepositories
public class Jina1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jina1Application.class, args);
    }

}
