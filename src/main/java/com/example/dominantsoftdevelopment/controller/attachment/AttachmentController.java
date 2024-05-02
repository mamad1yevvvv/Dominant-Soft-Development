package com.example.dominantsoftdevelopment.controller.attachment;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.AttachmentDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping(AttachmentController.BASE_PATH)
public interface AttachmentController {

    String BASE_PATH = "/attachment";
    String UPLOAD_PATH = "/upload";
    String UPLOAD_LIST_PATH = "/upload-list";

    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);

//    @PostMapping(UPLOAD_LIST_PATH)
//    ApiResult<List<AttachmentDTO>> uploadFile(List<MultipartHttpServletRequest> request);


    @GetMapping("{id}")
    ResponseEntity<?> downloadFile(@PathVariable Long id,
                                   @RequestParam(defaultValue = "inline") String view,
                                   HttpServletResponse response);

    @DeleteMapping
    HttpEntity<ApiResult<Boolean>> delete(Long id);
}