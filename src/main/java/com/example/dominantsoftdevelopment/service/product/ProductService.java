package com.example.dominantsoftdevelopment.service.product;

import com.example.dominantsoftdevelopment.dto.AddProductDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;

public interface ProductService {
    ApiResult<Boolean> add(AddProductDTO addProductDTO);
}
