package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.RoomRequest;
import io.toy.roomy.dto.response.admin.adminRoomListResponse;
import io.toy.roomy.dto.response.admin.adminStayListResponse;

import java.util.List;

public interface AdminRoomService {
    //객실조회
    List<adminRoomListResponse> getRoomList();
    //객실등록
    Room regRoom(RoomRequest room);
}
