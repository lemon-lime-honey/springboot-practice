package com.springboot.relationship.data.repository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;

@SpringBootTest
class ProductDetailRepositoryTest {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveAndReadTest1() {
        Product product = new Product();
        product.setName("Spring Boot JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트와 JPA를 함께 볼 수 있는 책");

        productDetailRepository.save(productDetail);

        System.out.println("savedProduct: " + productDetailRepository.findById(
            productDetail.getId()).get().getProduct());
        System.out.println("savedProductDetail: " + productDetailRepository.findById(
            productDetail.getId()).get());
    }
}
