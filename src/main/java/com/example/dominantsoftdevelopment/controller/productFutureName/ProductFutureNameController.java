package com.example.dominantsoftdevelopment.controller.productFutureName;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameCreateDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameUpdateDto;
import com.example.dominantsoftdevelopment.service.productFeatureName.ProductFeatureNameService;
import com.google.protobuf.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productFutureName")
public class ProductFutureNameController {

    private final ProductFeatureNameService productFeatureNameService;
    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@RequestBody ProductFeatureNameCreateDTO productFeatureNameDTO){
        return ResponseEntity.ok(productFeatureNameService.add(productFeatureNameDTO));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResult<ProductFeatureNameDTO>> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(productFeatureNameService.get(id));
    }

    @PutMapping("/update/{id}")
    public HttpEntity<ApiResult<ProductFeatureNameDTO>> update(@PathVariable("id") Long id ,
                                                               @RequestBody ProductFeatureNameUpdateDto updateDto){
        return ResponseEntity.ok(productFeatureNameService.update(id , updateDto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(productFeatureNameService.delete(id));
    }
}
