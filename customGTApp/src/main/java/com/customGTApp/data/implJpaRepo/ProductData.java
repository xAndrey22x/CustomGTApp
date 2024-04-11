package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.ProductContract;
import com.customGTApp.model.Product;
import com.customGTApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductData implements ProductContract {

    private final ProductRepo productRepo;

    @Autowired
    public ProductData(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepo.deleteById(id);
    }
}
