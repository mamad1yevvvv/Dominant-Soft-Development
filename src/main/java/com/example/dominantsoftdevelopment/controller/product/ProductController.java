package com.example.dominantsoftdevelopment.controller.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductDTOList;
import com.example.dominantsoftdevelopment.dto.ResponseFeatureDTO;
import com.example.dominantsoftdevelopment.service.product.ProductService;
import com.google.protobuf.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddProductDTO addProductDTO){
        return ResponseEntity.ok(productService.add(addProductDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/update/{id}")
    public HttpEntity<ApiResult<Boolean>> update(@Valid @NotNull @PathVariable Long id,@RequestBody AddProductDTO updateProductDTO){
        return ResponseEntity.ok(productService.update(id,updateProductDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or @productServiceImpl.isCreator(#id, principal.username)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Long id){
        return ResponseEntity.ok(productService.delete(id));
    }

    @GetMapping("/all-fieldByCategoryId")
    public HttpEntity<ApiResult<List<ResponseFeatureDTO>>> getAll(@Valid @NotNull Long categoryId){
        return ResponseEntity.ok(productService.allFields(categoryId));
    }

    @GetMapping("/active-product")
    public HttpEntity<ApiResult<List<ProductDTOList>>> getActiveProductBySellerId(){
        return ResponseEntity.ok(productService.getActiveProductByUserId());
    }
    @GetMapping("/noactive-product")
    public HttpEntity<ApiResult<List<ProductDTOList>>> getNoActiveProductBySellerId(){
        return ResponseEntity.ok(productService.getNoActiveProductByUserId());
    }
}
