package com.example.dominantsoftdevelopment.service.productFeatureName;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameCreateDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductFeatureNameService {
    ApiResult<Boolean> add(ProductFeatureNameCreateDTO featureNameDTO);

    ApiResult<ProductFeatureNameDTO> get(Long id);

    ApiResult<ProductFeatureNameDTO> update(Long id, ProductFeatureNameUpdateDto updateDto);

    ApiResult<Boolean> delete(Long id);
}
