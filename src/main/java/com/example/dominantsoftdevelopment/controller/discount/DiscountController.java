package com.example.dominantsoftdevelopment.controller.discount;

import com.example.dominantsoftdevelopment.dto.AddDiscountDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.DiscountDTO;
import com.example.dominantsoftdevelopment.service.discount.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public HttpEntity<ApiResult<Boolean>> createDiscount(@RequestBody @Valid AddDiscountDTO discountDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discountService.createDiscount(discountDTO));
    }

    @GetMapping("/{discountId}")
    public HttpEntity<ApiResult<DiscountDTO>> getDiscount(@PathVariable Long discountId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discountService.getDiscount(discountId));
    }

    @GetMapping("/list")
    public HttpEntity<ApiResult<Page<DiscountDTO>>> getDiscount(Pageable pageable, @RequestParam(required = false) String predicate) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discountService.getAll(pageable, predicate));
    }


    @PutMapping("/{discountId}")
    public HttpEntity<ApiResult<Boolean>> updateDiscount(@RequestBody @Valid AddDiscountDTO discountDTO,
                                                         @PathVariable Long discountId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(discountService.updateDiscount(discountId,discountDTO));
    }

    @DeleteMapping("/{discountId}")
    public HttpEntity<ApiResult<Void>> deleteDiscount(@PathVariable Long discountId) {
        discountService.deleteDiscount(discountId);
        return ResponseEntity.noContent().build();
    }




}
