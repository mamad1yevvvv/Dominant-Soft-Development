package com.example.dominantsoftdevelopment.service.favourite;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Favourite;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.repository.FavouriteRepository;
import com.example.dominantsoftdevelopment.repository.ProductRepository;
import com.example.dominantsoftdevelopment.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService{
    private final FavouriteRepository favouriteRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<Boolean> add(Long productId) {
        Product product = getProductById(productId);
        User user = CommonUtils.getCurrentUserFromContext();
        Favourite favourite = Favourite.builder()
                .user(user)
                .product(product)
                .build();
        favouriteRepository.save(favourite);


        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> remove(Long productId) {
        Product product = getProductById(productId);
        User user = CommonUtils.getCurrentUserFromContext();
        favouriteRepository.deleteByUserAndProduct(user, product);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<List<Product>> getAll() {
        User user = CommonUtils.getCurrentUserFromContext();
        return ApiResult.successResponse(favouriteRepository.findProductsByUser(user));
    }

    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> RestException.restThrow("product not found", HttpStatus.BAD_REQUEST));
    }
}
