package com.example.dominantsoftdevelopment.service.category;

import com.example.dominantsoftdevelopment.dto.AddCategoryDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<List<CategoryDTO>> all();

    ApiResult<Boolean> add(AddCategoryDTO addCategoryDTO);

    ApiResult<Boolean> update(Long id, AddCategoryDTO addCategoryDTO);

    ApiResult<Boolean> delete(Long id);
}
