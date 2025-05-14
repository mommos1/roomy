package io.toy.roomy.controller.admin;

import io.toy.roomy.common.FileUploadUtil;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.AdminStayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/stay")
public class AdminStayController {

    public final AdminStayService adminStayService;

    public AdminStayController(AdminStayService adminStayService) {
        this.adminStayService = adminStayService;
    }

    @PostMapping("/regStay")
    public ResponseEntity<?> adminRegStay(
            @RequestPart("stayDto") StayRequest dto,
            @RequestPart("imageFile") MultipartFile image) {

        try {
            String uploadDir = "D:/jb/study/roomy/src/main/resources/static/images/stayRegImage"; // 원하는 저장 경로
            String filePath = FileUploadUtil.saveFile(image, uploadDir);

            // savedFileName을 DB에 저장하거나, Stay 엔티티에 포함시키기
            // 예: stay.setImageUrl("/images/" + savedFileName);
            dto.setFilePath("/images/stayRegImage/" + filePath);

            adminStayService.regStay(dto);
            return ResponseEntity.ok(ApiResponse.success("숙소 등록 성공"));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 실패: " + e.getMessage());
        }
    }
}
