package io.toy.roomy.service;

import io.toy.roomy.dto.response.userStayListResponse;

import java.util.List;

public interface UserStayService {
    List<userStayListResponse> getStayListAll();
}
