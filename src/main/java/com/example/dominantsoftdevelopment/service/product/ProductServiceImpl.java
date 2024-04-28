package com.example.dominantsoftdevelopment.service.product;

import com.example.dominantsoftdevelopment.dto.*;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.*;
import com.example.dominantsoftdevelopment.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            System.out.println(productFeatures);
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


        if (!addProductDTO.getProductFeaturesDTOS().isEmpty()) {
            for (ProductFeaturesDTO productFeaturesDTO : addProductDTO.getProductFeaturesDTOS()) {

                ProductFeatureName productFeatureName = productFeaturesNameRepository.findById(productFeaturesDTO.getProductFeatureName().getId())
                        .orElseThrow(() -> RestException.restThrow("product feature not found", HttpStatus.BAD_REQUEST));

                ProductFeatures productFeatures = ProductFeatures.builder()
                        .product(product)
                        .productFeatureName(productFeatureName)
                        .build();

                System.out.println("productFeaturesDTO.getProductFutureValue() = " + productFeaturesDTO.getProductFutureValue());
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

        }
        return ApiResult.successResponse(true);
    }

    @Override
    @Transactional
    public ApiResult<Boolean> update(Long id, AddProductDTO updateProductDTO) {
        Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> RestException.restThrow("Product not found", HttpStatus.NO_CONTENT));
        List<ProductFeatures> featuresList = productFeaturesRepository.findByProduct_Id(id);
        List<Attachment> attachments = attachmentRepository.findAllById(updateProductDTO.getAttachmentIds());
        Category category = categoryRepository.findByIdAndDeletedFalse(updateProductDTO.getProductCategory()).orElseThrow(() -> RestException.restThrow("Category not found", HttpStatus.NO_CONTENT));
        User seller = userRepository.findById(updateProductDTO.getSellerId()).orElseThrow(() -> RestException.restThrow("Seller user not found", HttpStatus.NO_CONTENT));

        product.setProductName(updateProductDTO.getProductName());
        product.setConditionProduct(updateProductDTO.getConditionProduct());
        product.setDescription(updateProductDTO.getDescription());
        product.setPrice(updateProductDTO.getPrice());
        product.setPayType(updateProductDTO.getPayType());
        product.setAttachment(attachments);
        product.setProductCategory(category);
        product.setSeller(seller);
        productRepository.save(product);

        if (!featuresList.isEmpty()) {
            for (ProductFeatures productFeatures : featuresList) {

                ProductFeaturesDTO featuresDTO = updateProductDTO.getProductFeaturesDTOS().stream()
                        .filter(pr -> pr.getId().equals(productFeatures.getId())).findFirst().orElseThrow(() -> RestException.restThrow("Bunday feature mavjud emas", HttpStatus.NO_CONTENT));

                productFeatures.setProduct(product);
                productFeatures.setProductFeatureName(mapper.map(featuresDTO.getProductFeatureName(), ProductFeatureName.class));
                productFeatures.setValue(featuresDTO.getValue());
                productFeatures.setProductFutureValue(mapper.map(featuresDTO.getProductFutureValue(), ProductFeatureValue.class));
                productFeaturesRepository.save(productFeatures);
            }
        }

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Long id) {
        productRepository.findById(id).ifPresent(value -> value.setDeleted(true));
        return ApiResult.successResponse(true);
    }


    @Override
    public ApiResult<List<ResponseFeatureDTO>> allFields(Long categoryId) {
        List<ResponseFeatureDTO> responseFeatureDTOS = new ArrayList<>();

        for (ProductFeatureName name : productFeaturesNameRepository.findByCategory_Id(categoryId)) {
            ResponseFeatureDTO featureDTO = ResponseFeatureDTO.builder()
                    .isSelectable(false)
                    .productFeatureValueDTOList(null)
                    .productFeatureNameDTO(mapper.map(name, ProductFeatureNameDTO.class)).build();
            List<ProductFeatureValue> nameId = productFeatureValueRepository.findByProductFeatureName_Id(name.getId());
            if (!nameId.isEmpty()) {
                featureDTO.setProductFeatureValueDTOList(nameId.stream().map(productFeatureValue -> mapper.map(productFeatureValue, ProductFeatureValueDTO.class)).toList());
                featureDTO.setSelectable(true);
            }

            responseFeatureDTOS.add(featureDTO);
        }

        return ApiResult.successResponse(responseFeatureDTOS);
    }
}
