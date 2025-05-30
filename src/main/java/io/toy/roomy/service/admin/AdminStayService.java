package io.toy.roomy.service.admin;

import io.toy.roomy.dto.request.stay.StayRequest;
import io.toy.roomy.dto.request.stay.StayUpdateRequest;
import io.toy.roomy.dto.response.stay.StayDetailRecord;
import io.toy.roomy.dto.response.stay.StayListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminStayService {
    //숙소 등록
    void regStay(StayRequest dto, MultipartFile image) throws IOException;
    //숙소 목록조회
    List<StayListResponse> getAll();
    //숙소 단건조회
    StayDetailRecord getStayDetail(Long stayId);
    //숙소 수정
    void updateStay(StayUpdateRequest dto, MultipartFile image) throws IOException;
    //숙소 삭제
    void deleteStay(Long stayId);
}
