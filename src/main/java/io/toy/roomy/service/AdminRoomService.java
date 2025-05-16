package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.RoomRequest;

public interface AdminRoomService {
    //객실등록
    Room regRoom(RoomRequest room);

    //객실조회

}
