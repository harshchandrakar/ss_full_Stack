package com.harsh.yummy.controller;

import com.harsh.yummy.dto.CreateProductRequest;
import com.harsh.yummy.dto.CustomerRequest;
import com.harsh.yummy.dto.ProductResponse;
import com.harsh.yummy.entity.Product;
import com.harsh.yummy.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService prodService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateProductRequest request) {
        return ResponseEntity.ok(prodService.createProduct(request));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> allProduct() {
        return ResponseEntity.ok(prodService.allProduct());
    }
    @PatchMapping
    public ResponseEntity<String> updateProduct( @RequestParam String pid,@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(prodService.updateProduct(pid,request));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam String pid) {
        return ResponseEntity.ok(prodService.deleteProduct(pid));
    }
    @GetMapping(path = "/2")
    public ResponseEntity<List<Product>> deleteProduct(@RequestParam Float mn, @RequestParam Float mx) {
        return ResponseEntity.ok(prodService.getTop2ProductsInRange(mn,mx));
    }


}
