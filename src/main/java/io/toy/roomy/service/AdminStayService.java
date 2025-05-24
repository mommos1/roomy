package io.toy.roomy.service;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.response.admin.adminStayListResponse;

import java.util.List;

public interface AdminStayService {

    Stay regStay(StayRequest dto);

    List<adminStayListResponse> getAll();
    //숙소삭제
    void deleteStay(Long stayId);
}
