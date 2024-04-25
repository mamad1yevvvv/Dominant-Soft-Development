package com.example.dominantsoftdevelopment.service.category;

import com.example.dominantsoftdevelopment.dto.AddCategoryDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.CategoryDTO;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Attachment;
import com.example.dominantsoftdevelopment.model.Category;
import com.example.dominantsoftdevelopment.repository.AttachmentRepository;
import com.example.dominantsoftdevelopment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final ModelMapper mapper;

    @Override
    public ApiResult<List<CategoryDTO>> all() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryRepository.findByParentCategoryIsNullAndDeletedFalse()) {
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .attachment(category.getAttachment())
                    .categoryDTOList(findChildCategory(category.getId()))
                    .build();
            categoryDTOList.add(categoryDTO);
        }

        return ApiResult.successResponse(categoryDTOList);
    }

    private List<CategoryDTO> findChildCategory(Long parentCategoryId) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categoryRepository.findByParentCategoryIdAndDeletedFalse(parentCategoryId)) {

            List<CategoryDTO> categoryDTOS = null;

            if (category.getParentCategory() != null)
                categoryDTOS = findChildCategory(category.getId());

            CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);

            if (category.getParentCategory() != null)
                categoryDTO.setCategoryDTOList(categoryDTOS);

            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @Override
    public ApiResult<Boolean> add(AddCategoryDTO addCategoryDTO) {
        Attachment attachment = null;
        Category category  = null;
        if (addCategoryDTO.getAttachmentId()!=null){
            attachment = attachmentRepository.findById(addCategoryDTO.getAttachmentId()).orElseThrow(() -> RestException.restThrow("Photo not found ",HttpStatus.NO_CONTENT));
        }
        if (addCategoryDTO.getCategoryId()!=null){
            category = categoryRepository.findById(addCategoryDTO.getCategoryId()).orElseThrow(() -> RestException.restThrow("Parent Category not Found",HttpStatus.NO_CONTENT));
        }

        Category category1 = Category.builder()
                .name(addCategoryDTO.getName())
                .parentCategory(category)
                .attachment(attachment).build();

        categoryRepository.save(category1);
        return ApiResult.successResponse(true);
    }


    @Override
    public ApiResult<Boolean> update(Long id, AddCategoryDTO addCategoryDTO) {
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> RestException.restThrow("category not found", HttpStatus.BAD_REQUEST));
        Category parrentCategory = categoryRepository.findByIdAndDeletedFalse(addCategoryDTO.getCategoryId()).orElseThrow(() -> RestException.restThrow("ParentCategory not found", HttpStatus.BAD_REQUEST));
        Attachment photo = attachmentRepository.findById(addCategoryDTO.getAttachmentId()).orElseThrow(() -> RestException.restThrow("photo not found", HttpStatus.BAD_REQUEST));

        category.setName(addCategoryDTO.getName());
        category.setParentCategory(parrentCategory);
        category.setAttachment(photo);

        categoryRepository.save(category);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setDeleted(true);
            categoryRepository.save(category);
        }
        return ApiResult.successResponse(true);
    }
}
