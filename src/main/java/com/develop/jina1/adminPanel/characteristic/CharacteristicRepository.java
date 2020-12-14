package com.develop.jina1.adminPanel.characteristic;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends QuerydslJdbcRepository<Characteristic, Long> {
    boolean existsByName(String name);
}
