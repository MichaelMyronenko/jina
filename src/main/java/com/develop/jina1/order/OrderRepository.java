package com.develop.jina1.order;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends QuerydslJdbcRepository<Order, Long> {
    Set<Order> findAllByUserId(Long userId);
}
