package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.response.room.RoomRecord;
import io.toy.roomy.dto.response.room.AdminRoomListResponse;

import java.util.List;

public interface RoomService {
    //객실 목록 조회
    List<RoomRecord> getRoomList(Long stayId);
    //객실 정보 조회
    RoomRecord getRoomDetail(Long roomId);
    //객실등록
    Room regRoom(RoomRequest room);
    //객실삭제
    void deleteRoom(Long roomId);
    //객실수정
    void updateRoom(Long roomId, RoomRequest request);
}
