package io.toy.roomy.service;

import io.toy.roomy.dto.response.user.userStayListResponse;

import java.util.List;

public interface UserStayService {
    List<userStayListResponse> getStayListAll();
}
