package com.example.dominantsoftdevelopment.controller.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductDTOList;
import com.example.dominantsoftdevelopment.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public HttpEntity<ApiResult<List<ProductDTOList>>> list(){
        return ResponseEntity.ok(productService.list());
    }

    @PostMapping
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddProductDTO addProductDTO){
        return ResponseEntity.ok(productService.add(addProductDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Long id){
        return ResponseEntity.ok(productService.delete(id));
    }
}
