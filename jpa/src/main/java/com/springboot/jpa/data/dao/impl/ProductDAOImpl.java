package com.springboot.jpa.data.dao.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;

@Component
public class ProductDAOImpl implements ProductDAO {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);

        return saveProduct;
    }

    @Override
    public Product selectProduct(Long nubmber) {
        Product selectedProduct = productRepository.getReferenceById(nubmber);

        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
