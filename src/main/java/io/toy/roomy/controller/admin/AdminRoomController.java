package io.toy.roomy.controller.admin;

import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자페이지 내 객실 관련 RestController
 */
@RestController
@RequestMapping("/admin/room")
public class AdminRoomController {

    public final RoomService adminRoomService;

    public AdminRoomController(RoomService adminRoomService) {
        this.adminRoomService = adminRoomService;
    }

    /**
     * 객실 등록
     * @param dto 객실등록 정보
     * @return 응답 메시지
     */
    @PostMapping
    public ResponseEntity<?> adminRegStay(@RequestBody RoomRequest dto) {
        adminRoomService.regRoom(dto);
        return ResponseEntity.ok(ApiResponse.success("객실 등록 성공"));
    }

    /**
     * 객실 수정
     * @param roomId 수정 객실 ID
     * @return 응답 메시지
     */
    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateRoom(
            @PathVariable Long roomId,
            @RequestBody RoomRequest dto) {
        adminRoomService.updateRoom(roomId, dto);
        return ResponseEntity.ok(ApiResponse.success("객실 수정 성공"));
    }

    /**
     * 객실 삭제
     * @param roomId 삭제 객실 ID
     * @return 응답 메시지
     */
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        adminRoomService.deleteRoom(roomId);
        return ResponseEntity.ok(ApiResponse.success("객실 삭제 성공"));
    }
}
