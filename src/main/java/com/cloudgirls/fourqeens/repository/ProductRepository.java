package com.cloudgirls.fourqeens.repository;

import java.util.*;

import com.cloudgirls.fourqeens.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
