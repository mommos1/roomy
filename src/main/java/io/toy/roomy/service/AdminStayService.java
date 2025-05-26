package io.toy.roomy.service;

import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.request.StayUpdateRequest;
import io.toy.roomy.dto.response.admin.StayDetailResponse;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminStayService {
    //숙소 등록
    void regStay(StayRequest dto, MultipartFile image) throws IOException;
    //숙소 목록조회
    List<adminStayListResponse> getAll();
    //숙소 단건조회
    StayDetailResponse getStayDetail(Long stayId);
    //숙소 수정
    void updateStay(StayUpdateRequest dto, MultipartFile image) throws IOException;
    //숙소 삭제
    void deleteStay(Long stayId);
}
