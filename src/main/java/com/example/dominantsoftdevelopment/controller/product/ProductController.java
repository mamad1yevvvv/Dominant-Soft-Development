package com.example.dominantsoftdevelopment.controller.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddProductDTO addProductDTO){
        return ResponseEntity.ok(productService.add(addProductDTO));
    }
}
