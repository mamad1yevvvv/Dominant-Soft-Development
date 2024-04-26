package com.example.dominantsoftdevelopment.controller.productFeaturesvalue;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueCreateAndUpdateDto;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueResponseDto;
import com.example.dominantsoftdevelopment.service.productFeaturesvalue.ProductFeatureValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productFeaturesValue")
public class ProductFeatureValueController {

    private final ProductFeatureValueService productFeatureValueService;

    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@RequestBody ProductFeatureValueCreateAndUpdateDto productFeatureValueCreateDTO){
        return ResponseEntity.ok(productFeatureValueService.add(productFeatureValueCreateDTO));
    }

    @GetMapping("/{id}/get")
    public HttpEntity<ApiResult<ProductFeatureValueResponseDto>> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(productFeatureValueService.get(id));
    }

    @PutMapping("/{id}/update")
    public HttpEntity<ApiResult<ProductFeatureValueResponseDto>> update(@PathVariable("id") Long id , ProductFeatureValueCreateAndUpdateDto updateDto){
        return ResponseEntity.ok(productFeatureValueService.update(id , updateDto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(productFeatureValueService.delete(id));
    }
}
