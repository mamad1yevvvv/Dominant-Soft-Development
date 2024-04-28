package com.example.dominantsoftdevelopment.service.productFeatureName;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameCreateDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameDTO;
import com.example.dominantsoftdevelopment.dto.ProductFeatureNameUpdateDto;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Category;
import com.example.dominantsoftdevelopment.model.ProductFeatureName;
import com.example.dominantsoftdevelopment.repository.CategoryRepository;
import com.example.dominantsoftdevelopment.repository.ProductFeaturesNameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFeatureNameServiceImpl implements ProductFeatureNameService{

    private final CategoryRepository categoryRepository;
    private final ProductFeaturesNameRepository featuresNameRepository;
    private final ModelMapper mapper;
    @Override
    public ApiResult<Boolean> add(ProductFeatureNameCreateDTO featureNameDTO) {

        Category category = categoryRepository.findById(featureNameDTO.getCategoryId()).orElseThrow(() ->
                RestException.restThrow("category id  " + featureNameDTO.getCategoryId() + " not found "));

        ProductFeatureName productFeatureName = new ProductFeatureName();

        if (category.getParentCategory().getId() != null) {
            productFeatureName.setName(featureNameDTO.getName());
            productFeatureName.setMeasure(featureNameDTO.getMeasure());
            productFeatureName.setCategory(category);
        }

        featuresNameRepository.save(productFeatureName);

        return  ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<ProductFeatureNameDTO> get(Long id) {
        ProductFeatureName productFeatureName = featuresNameRepository.findById(id).orElseThrow(() ->
                RestException.restThrow("ProductFeatureName " + id + " not found"));

        ProductFeatureNameDTO productFeatureNameDTO = mapper.map(productFeatureName, ProductFeatureNameDTO.class);
        productFeatureNameDTO.setCategoryId(productFeatureName.getCategory().getId());
        return ApiResult.successResponse(productFeatureNameDTO);
    }

    @Override
    public ApiResult<ProductFeatureNameDTO> update(Long id, ProductFeatureNameUpdateDto updateDto) {

        Category category = categoryRepository.findById(updateDto.getCategoryId()).orElseThrow(() -> RestException.restThrow("category id " + id + " not found"));

        ProductFeatureName productFeatureName = featuresNameRepository.findById(id).orElseThrow(() -> RestException.restThrow("ProductFeatureName id " + id + " not found"));

        productFeatureName.setCategory(category);
        productFeatureName.setMeasure(updateDto.getMeasure());
        productFeatureName.setName(updateDto.getName());

        featuresNameRepository.save(productFeatureName);

        ProductFeatureNameDTO productFeatureNameDTO = mapper.map(productFeatureName, ProductFeatureNameDTO.class);
        productFeatureNameDTO.setCategoryId(productFeatureName.getCategory().getId());
        return ApiResult.successResponse(productFeatureNameDTO);
    }

    @Override
    public ApiResult<Boolean> delete(Long id) {

        ProductFeatureName productFeatureName = featuresNameRepository.findById(id).orElseThrow(() -> RestException.restThrow("ProductFeatureName id " + id + "not found"));
        featuresNameRepository.delete(productFeatureName);
        return ApiResult.successResponse(true);
    }
}
