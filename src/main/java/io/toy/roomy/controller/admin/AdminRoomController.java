package io.toy.roomy.controller.admin;

import io.toy.roomy.dto.request.RoomRequest;
import io.toy.roomy.dto.response.ApiResponse;
import io.toy.roomy.service.AdminRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/room")
public class AdminRoomController {

    public final AdminRoomService adminRoomService;

    public AdminRoomController(AdminRoomService adminRoomService) {
        this.adminRoomService = adminRoomService;
    }

    @PostMapping("/regRoom")
    public ResponseEntity<?> adminRegStay(@RequestBody RoomRequest dto) {
        adminRoomService.regRoom(dto);
        return ResponseEntity.ok(ApiResponse.success("등록 성공"));
    }

    @DeleteMapping("/delete/room/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        adminRoomService.deleteRoom(roomId);
        return ResponseEntity.ok(ApiResponse.success("삭제 성공"));
    }
}
