package com.example.dominantsoftdevelopment.service.productFeaturesvalue;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueCreateAndUpdateDto;
import com.example.dominantsoftdevelopment.dto.ProductFeatureValueResponseDto;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.ProductFeatureName;
import com.example.dominantsoftdevelopment.model.ProductFeatureValue;
import com.example.dominantsoftdevelopment.repository.ProductFeatureValueRepository;
import com.example.dominantsoftdevelopment.repository.ProductFeaturesNameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFeatureValueServiceImpl implements ProductFeatureValueService{

    private final ProductFeaturesNameRepository productFeaturesNameRepository;
    private final ProductFeatureValueRepository featureValueRepository;
    private final ModelMapper modelMapper;
    @Override
    public ApiResult<Boolean> add(ProductFeatureValueCreateAndUpdateDto productFeatureValueCreateDTO) {
        ProductFeatureName productFeatureName = productFeaturesNameRepository.findById(productFeatureValueCreateDTO.getProductFeatureNameId()).
                orElseThrow(() -> RestException.restThrow("ProductFeaturesName id ="
                        + productFeatureValueCreateDTO.getProductFeatureNameId() + " not found"));

        ProductFeatureValue productFeatureValue = ProductFeatureValue.builder()
                .productFeatureName(productFeatureName)
                .value(productFeatureValueCreateDTO.getValue())
                .build();

        featureValueRepository.save(productFeatureValue);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<ProductFeatureValueResponseDto> get(Long id) {
        ProductFeatureValue productFeatureValue = featureValueRepository.findById(id).orElseThrow(() ->
                RestException.restThrow("ProductFeatureValue id= " + id + " not found"));

        ProductFeatureValueResponseDto valueResponseDto = modelMapper.map(productFeatureValue, ProductFeatureValueResponseDto.class);
        valueResponseDto.setProductFeatureNameId(productFeatureValue.getProductFeatureName().getId());
        return ApiResult.successResponse(valueResponseDto);
    }

    @Override
    public ApiResult<ProductFeatureValueResponseDto> update(Long id, ProductFeatureValueCreateAndUpdateDto updateDto) {

        ProductFeatureName productFeatureName = productFeaturesNameRepository.findById(updateDto.getProductFeatureNameId()).
                orElseThrow(() -> RestException.restThrow("ProductFeatureName id= " + updateDto.getProductFeatureNameId() + " not found"));

        ProductFeatureValue productFeatureValue = featureValueRepository.findById(id).orElseThrow(() -> RestException.restThrow("ProductFeatureValue id= " + id + " not found"));


            productFeatureValue.setProductFeatureName(productFeatureName);
            productFeatureValue.setValue(updateDto.getValue());


        featureValueRepository.save(productFeatureValue);

        ProductFeatureValueResponseDto valueResponseDto = modelMapper.map(productFeatureValue, ProductFeatureValueResponseDto.class);
        valueResponseDto.setProductFeatureNameId(productFeatureName.getId());
        return ApiResult.successResponse(valueResponseDto);
    }

    @Override
    public ApiResult<Boolean> delete(Long id) {

        ProductFeatureValue productFeatureValue = featureValueRepository.findById(id).orElseThrow(() -> RestException.restThrow("ProductFeatureValue id= " + id + " not found"));

        featureValueRepository.delete(productFeatureValue);
        return ApiResult.successResponse(true);
    }
}
