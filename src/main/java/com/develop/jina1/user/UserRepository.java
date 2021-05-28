package com.develop.jina1.user;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

import java.util.Optional;

public interface UserRepository extends QuerydslJdbcRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
