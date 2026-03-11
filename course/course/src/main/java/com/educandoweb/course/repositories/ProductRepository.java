package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.projection.ProductNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductNameProjection> findAllProjectedBy();
}


