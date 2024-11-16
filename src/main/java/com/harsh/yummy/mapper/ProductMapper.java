package com.harsh.yummy.mapper;

import com.harsh.yummy.dto.CreateProductRequest;
import com.harsh.yummy.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
}
