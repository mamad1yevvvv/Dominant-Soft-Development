package com.example.dominantsoftdevelopment.service.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductDTOList;
import com.example.dominantsoftdevelopment.dto.ProductFeaturesDTO;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.ProductFeatureName;
import com.example.dominantsoftdevelopment.model.ProductFeatureValue;
import com.example.dominantsoftdevelopment.model.ProductFeatures;
import com.example.dominantsoftdevelopment.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductFeaturesNameRepository productFeaturesNameRepository;
    private final ProductFeatureValueRepository productFeatureValueRepository;
    private final ProductFeaturesRepository productFeaturesRepository;
    private final ModelMapper mapper;


    @Override
    public ApiResult<List<ProductDTOList>> list() {
        List<ProductDTOList> list = productRepository.findAllByDeletedFalse().stream()
                .map(product -> mapper.map(product, ProductDTOList.class)).toList();

        for (ProductDTOList productDTOList : list) {
            List<ProductFeatures> productFeatures = productFeaturesRepository.findByProduct_IdAndDeletedFalse(productDTOList.getId());
            productDTOList.setProductDTOLists(productFeatures.stream()
                    .map(productFeatures1 -> mapper.map(productFeatures1, ProductFeaturesDTO.class)).toList());
        }

        return ApiResult.successResponse(list);
    }

    @Override
    @Transactional
    public ApiResult<Boolean> add(AddProductDTO addProductDTO) {

        Product product = Product.builder()
                .price(addProductDTO.getPrice())
                .availability(addProductDTO.getAvailability())
                .conditionProduct(addProductDTO.getConditionProduct())
                .productName(addProductDTO.getProductName())
                .description(addProductDTO.getDescription())
                .payType(addProductDTO.getPayType())
                .attachment(attachmentRepository.findAllById(addProductDTO.getAttachmentIds()))
                .seller(userRepository.findById(addProductDTO.getSellerId()).orElseThrow(() -> RestException
                        .restThrow("Seller user not found", HttpStatus.BAD_REQUEST)))
                .productCategory(categoryRepository.findByIdAndDeletedFalse(addProductDTO.getProductCategory())
                        .orElseThrow(() -> RestException.restThrow("category not found", HttpStatus.BAD_REQUEST))).build();
        productRepository.save(product);

        for (ProductFeaturesDTO productFeaturesDTO : addProductDTO.getProductFeaturesDTOS()) {
            System.out.println(productFeaturesDTO.getProductFeatureName().getId());
            ProductFeatureName productFeatureName = productFeaturesNameRepository.findById(productFeaturesDTO.getProductFeatureName().getId())
                    .orElseThrow(() -> RestException.restThrow("product feature not found", HttpStatus.BAD_REQUEST));

            ProductFeatures productFeatures = ProductFeatures.builder()
                    .product(product)
                    .productFeatureName(productFeatureName)
                    .build();

            if (productFeaturesDTO.getProductFutureValue() != null) {
                ProductFeatureValue productFeatureValue = ProductFeatureValue.builder()
                        .productFeatureName(productFeatureName)
                        .value(productFeaturesDTO.getProductFutureValue().getValue()).build();
                productFeatureValueRepository.save(productFeatureValue);
                productFeatures.setProductFutureValue(productFeatureValue);
            } else {
                productFeatures.setValue(productFeaturesDTO.getValue());
            }
            productFeaturesRepository.save(productFeatures);
        }
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Long id) {
        productRepository.findById(id).ifPresent(value -> value.setDeleted(true));
        return ApiResult.successResponse(true);
    }
}
