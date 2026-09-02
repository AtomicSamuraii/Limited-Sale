package com.AtomicSamurai.LimitedSale.Service;


import com.AtomicSamurai.LimitedSale.Entity.Product;
import com.AtomicSamurai.LimitedSale.Repository.ProductRepository;
import com.AtomicSamurai.LimitedSale.dto.CreateProductRequest;
import com.AtomicSamurai.LimitedSale.dto.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product createdProd = productRepository.save(product);
        return toResponse(createdProd);
    }

    public ProductResponse getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not Found"));
        return toResponse(product);
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt()
        );
    }
}
