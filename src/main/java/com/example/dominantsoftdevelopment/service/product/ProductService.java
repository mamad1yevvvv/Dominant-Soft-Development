package com.example.dominantsoftdevelopment.service.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductDTOList;
import com.example.dominantsoftdevelopment.dto.ResponseFeatureDTO;

import java.util.List;

public interface ProductService {
    ApiResult<Boolean> add(AddProductDTO addProductDTO);

    ApiResult<List<ProductDTOList>> list();

    ApiResult<Boolean> delete(Long id);

    ApiResult<List<ResponseFeatureDTO>> allFields(Long categoryId);

    Boolean isCreator(Long id, String username);

    ApiResult<Boolean> update(Long id, AddProductDTO updateProductDTO);

    ApiResult<List<ProductDTOList>> getActiveProductByUserId();

    ApiResult<List<ProductDTOList>> getNoActiveProductByUserId();
}
