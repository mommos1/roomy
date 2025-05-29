package io.toy.roomy.service.admin;

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.request.room.RoomUpdateRequest;
import io.toy.roomy.dto.response.room.RoomDetailResponse;
import io.toy.roomy.dto.response.room.adminRoomListResponse;

import java.util.List;

public interface AdminRoomService {
    //객실 목록 조회
    List<adminRoomListResponse> getRoomList(Long stayId);
    //객실 정보 조회
    RoomDetailResponse getRoomDetail(Long roomId);
    //객실등록
    Room regRoom(RoomRequest room);
    //객실삭제
    void deleteRoom(Long roomId);
    //객실수정
    void updateRoom(Long roomId, RoomUpdateRequest request);
}
