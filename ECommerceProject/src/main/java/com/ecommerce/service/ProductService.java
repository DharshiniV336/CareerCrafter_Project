package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    public void save(Product product) {
        long id=(long)(Math.random()*10000);
        product.setId(id);

        product.setCategory(categoryRepository.findOrSave(product.getCategory()));
        product.setVendor(vendorRepository.findOrSave(product.getVendor()));
        productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public void updateStock(Long id, int newQuantity) {
        productRepository.updateStock(id,newQuantity);
    }

    public Map<String, Integer> countProductsByVendor() {
        return productRepository.countProductByVendor();
    }

}
