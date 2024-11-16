package com.harsh.yummy.service;


import com.harsh.yummy.dto.CreateProductRequest;
import com.harsh.yummy.dto.ProductResponse;
import com.harsh.yummy.entity.Product;
import com.harsh.yummy.mapper.ProductMapper;
import com.harsh.yummy.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String createProduct(CreateProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Created";
    }

    public List<ProductResponse> allProduct() {
        List<Product> products = repo.findAll();

        return products.stream().map(product -> new ProductResponse(product.getId().toString(),product.getName(),product.getPrice())).collect(Collectors.toList());
    }
    public String updateProduct(String pid,CreateProductRequest request ) {
        UUID uuid = UUID.fromString(pid);
        Optional<Product> productOPt = repo.findById(uuid);
        Product product = productOPt.orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }

        repo.save(product);

        return "Product Updated Successfully";

    }
    public String deleteProduct(String pid) {
        UUID uuid = UUID.fromString(pid);
        Optional<Product> productOPt = repo.findById(uuid);
        Product product = productOPt.orElseThrow(() -> new RuntimeException("Product not found"));

        repo.delete(product);

        return "Product Deleted Successfully";

    }
    public List<Product> getTop2ProductsInRange(float minPrice, float maxPrice) {
        // Fetch all products in the range and return the top 2
        return repo.findTopProductsInRange(minPrice, maxPrice).stream()
                .limit(2)
                .toList();
    }
}
