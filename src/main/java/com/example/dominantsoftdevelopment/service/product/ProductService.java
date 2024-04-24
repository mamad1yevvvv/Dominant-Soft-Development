package com.example.dominantsoftdevelopment.service.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.ProductDTOList;

import java.util.List;

public interface ProductService {
    ApiResult<Boolean> add(AddProductDTO addProductDTO);

    ApiResult<List<ProductDTOList>> list();

    ApiResult<Boolean> delete(Long id);
}
