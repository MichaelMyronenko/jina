package com.develop.jina1.security.rereshToken;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RefreshTokenRepository extends QuerydslJdbcRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUuId(String uuId);

    Optional<RefreshToken> findByUserId(Long userId);
}
