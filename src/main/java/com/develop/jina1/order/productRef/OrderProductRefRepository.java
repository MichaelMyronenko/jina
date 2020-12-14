package com.develop.jina1.order.productRef;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRefRepository extends QuerydslJdbcRepository<OrderProductRef, Long> {

}