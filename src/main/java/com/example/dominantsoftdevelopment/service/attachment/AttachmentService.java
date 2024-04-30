package com.example.dominantsoftdevelopment.service.attachment;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.AttachmentDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface AttachmentService {

    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);

    ApiResult<List<AttachmentDTO>> uploadFiles(List<MultipartFile> requests);

    ResponseEntity<?> downloadFile(Long id, String view, HttpServletResponse response);

    HttpEntity<ApiResult<Boolean>> delete(Long id);
}
