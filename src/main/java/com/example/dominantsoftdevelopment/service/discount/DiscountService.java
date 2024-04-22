package com.example.dominantsoftdevelopment.service.discount;

import com.example.dominantsoftdevelopment.dto.AddDiscountDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.DiscountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService {

    ApiResult<Boolean> createDiscount(AddDiscountDTO discountDTO);

    ApiResult<Boolean> updateDiscount(Long discountId, AddDiscountDTO discountDTO);

    void deleteDiscount(Long discountId);

    ApiResult<DiscountDTO> getDiscount(Long discountId);

    ApiResult<Page<DiscountDTO>> getAll(Pageable pageable, String predicate);
}
