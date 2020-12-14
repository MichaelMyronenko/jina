package com.develop.jina1.adminPanel.category;

import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends QuerydslJdbcRepository<Category, Long> {
    boolean existsByTitle(String title);

    List<Category> findAll();
}
