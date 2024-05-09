package com.example.dominantsoftdevelopment.controller.favourite;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.service.favourite.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favourite")
public class FavouriteController {

    private final FavouriteService favouriteService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ApiResult<Boolean>> addToFavourites(@PathVariable Long productId) {
        return ResponseEntity.ok(favouriteService.add(productId));
    }
    @PostMapping("/remove/{productId}")
    public ResponseEntity<ApiResult<Boolean>> removeFromFavourites(@PathVariable Long productId) {
        return ResponseEntity.ok(favouriteService.remove(productId));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResult<List<Product>>> getAllUserFavourites() {
        return ResponseEntity.ok(favouriteService.getAll());
    }


}
