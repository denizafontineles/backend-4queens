package com.cloudgirls.fourqeens.repository;

import com.cloudgirls.fourqeens.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
