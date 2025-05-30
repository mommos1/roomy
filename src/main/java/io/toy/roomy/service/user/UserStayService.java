package io.toy.roomy.service.user;

import io.toy.roomy.dto.response.room.RoomDetailRecord;
import io.toy.roomy.dto.response.stay.StayDetailRecord;
import io.toy.roomy.dto.response.stay.StayListResponse;

import java.util.List;
import java.util.Map;

public interface UserStayService {
    // 숙소 목록 조회
    List<StayListResponse> getStayListAll();
    // 숙소 상세 조회
    StayDetailRecord getStayDetail(Long stayId);
    // 숙소에 연결된 객실 리스트 조회
    List<RoomDetailRecord> getRoomsByStayID(Long id);
}
