package com.springboot.test.data.repository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.test.data.entity.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void basicCRUDTest() {
        Product givenProduct = Product.builder()
            .name("노트")
            .price(1000)
            .stock(500)
            .build();

        Product savedProduct = productRepository.save(givenProduct);

        Assertions.assertThat(savedProduct.getNumber())
            .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(savedProduct.getName())
            .isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice())
            .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock())
            .isEqualTo(givenProduct.getStock());

        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
            .orElseThrow(RuntimeException::new);

        Assertions.assertThat(selectedProduct.getNumber())
            .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
            .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
            .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
            .isEqualTo(givenProduct.getStock());

        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
            .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        assertEquals(updatedProduct.getName(), "장난감");

        productRepository.delete(updatedProduct);

        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
    }
}
