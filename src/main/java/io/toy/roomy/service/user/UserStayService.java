package io.toy.roomy.service.user;

import io.toy.roomy.dto.response.stay.StayListResponse;

import java.util.List;

public interface UserStayService {
    // 숙소 목록 조회
    List<StayListResponse> getStayListAll();
}
