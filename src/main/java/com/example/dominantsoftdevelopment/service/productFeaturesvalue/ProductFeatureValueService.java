package com.example.dominantsoftdevelopment.service.productFeaturesvalue;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueCreateAndUpdateDto;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueResponseDto;
import com.example.dominantsoftdevelopment.model.ProductFeatureValue;
import org.springframework.stereotype.Service;

@Service
public interface ProductFeatureValueService {
    ApiResult<Boolean> add(ProductFeatureValueCreateAndUpdateDto productFeatureValueCreateDTO);

    ApiResult<ProductFeatureValueResponseDto> get(Long id);

    ApiResult<ProductFeatureValueResponseDto> update(Long id, ProductFeatureValueCreateAndUpdateDto updateDto);

    ApiResult<Boolean> delete(Long id);
}
