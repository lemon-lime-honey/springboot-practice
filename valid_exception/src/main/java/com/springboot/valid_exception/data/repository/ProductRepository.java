package com.springboot.valid_exception.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.valid_exception.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
