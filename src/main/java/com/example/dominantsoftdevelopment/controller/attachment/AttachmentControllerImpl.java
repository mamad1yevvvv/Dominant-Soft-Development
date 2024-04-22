package com.example.dominantsoftdevelopment.controller.attachment;


import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.AttachmentDTO;
import com.example.dominantsoftdevelopment.service.attachment.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public record AttachmentControllerImpl(AttachmentService attachmentService) implements AttachmentController {


    @Override
    public ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

//    @Override
//    @SuppressWarnings(("unchecked"))
//    public ApiResult<List<AttachmentDTO>> uploadFile(Lis request) {
//        return attachmentService.uploadFiles(request);
//    }

    @Override
    public ResponseEntity<?> downloadFile(Long id, String view, HttpServletResponse response) {
        return attachmentService.downloadFile(id, view, response);
    }
}
