package com.example.dominantsoftdevelopment.controller.category;

import com.example.dominantsoftdevelopment.dto.AddCategoryDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.CategoryDTO;
import com.example.dominantsoftdevelopment.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public HttpEntity<ApiResult<List<CategoryDTO>>> list(){
      return ResponseEntity.ok(categoryService.all());
    }

    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@RequestBody AddCategoryDTO addCategoryDTO){
        return ResponseEntity.ok(categoryService.add(addCategoryDTO));
    }

    @PutMapping("/update/{id}")
    public HttpEntity<ApiResult<Boolean>> update(@PathVariable Long id,@RequestBody AddCategoryDTO addCategoryDTO){
        return ResponseEntity.ok(categoryService.update(id,addCategoryDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.delete(id));
    }



}
