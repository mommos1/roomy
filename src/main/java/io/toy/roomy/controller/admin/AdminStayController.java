package io.toy.roomy.controller.admin;

import io.toy.roomy.dto.request.stay.StayRequest;
import io.toy.roomy.dto.request.stay.StayUpdateRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.StayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 관리자페이지 내 숙소 관련 RestController
 */
@RestController
@RequestMapping("/admin/stay")
public class AdminStayController {

    public final StayService adminStayService;

    public AdminStayController(StayService adminStayService) {
        this.adminStayService = adminStayService;
    }

    /**
     * 숙소 등록
     * @param dto 숙소 정보
     * @param image 숙소 대표 이미지
     * @return 등록 결과
     */
    @PostMapping
    public ResponseEntity<?> adminRegStay(
            @RequestPart("stayDto") StayRequest dto,
            @RequestPart("imageFile") MultipartFile image) {

        try {
            adminStayService.regStay(dto, image);
            return ResponseEntity.ok(ApiResponse.success("숙소 등록 성공"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 숙소 수정
     * @param dto
     * @param image
     * @return
     */
    @PutMapping("/{stayId}")
    public ResponseEntity<?> adminUpdateStay(
            @PathVariable Long stayId,
            @RequestPart("stayDto") StayUpdateRequest dto,
            @RequestPart(value = "imageFile", required = false) MultipartFile image) {
        
        try {
            dto.setId(stayId);
            adminStayService.updateStay(dto, image);
            return ResponseEntity.ok(ApiResponse.success("숙소 수정 성공"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 숙소 삭제
     * @param stayId 삭제 숙소 ID
     * @return 응답 메시지
     */
    @DeleteMapping("/{stayId}")
    public ResponseEntity<?> adminDeleteStay(@PathVariable Long stayId) {
        adminStayService.deleteStay(stayId);
        return ResponseEntity.ok(ApiResponse.success("숙소 삭제 성공"));
    }
}
