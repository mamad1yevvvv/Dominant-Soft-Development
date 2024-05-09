package com.example.dominantsoftdevelopment.service.favourite;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.model.Product;

import java.util.List;

public interface FavouriteService {
    ApiResult<List<Product>> getAll();

    ApiResult<Boolean> add(Long productId);

    ApiResult<Boolean> remove(Long productId);

}
