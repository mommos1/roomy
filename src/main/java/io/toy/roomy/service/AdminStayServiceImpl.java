package io.toy.roomy.service;

import io.toy.roomy.common.FileUploadUtil;
import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.request.StayUpdateRequest;
import io.toy.roomy.dto.response.admin.StayDetailResponse;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
import io.toy.roomy.repository.StayRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdminStayServiceImpl implements AdminStayService {

    private final StayRepository reserveRepository;

    public AdminStayServiceImpl(StayRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }
    
    //숙소 대표이미지 저장 경로
    private final String uploadDir = "/resources/static/images/stayRegImage";

    /**
     * 숙소 등록
     * @param dto   숙소 정보
     * @param image 숙소 대표 이미지
     * @throws IOException IOException
     */
    @Transactional
    @Override
    public void regStay(
            StayRequest dto,
            MultipartFile image) throws IOException {
        
        String filePath = FileUploadUtil.saveFile(image, uploadDir);

        Stay stay = Stay.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .type(dto.getType())
                .filePath(dto.getFilePath())
                .orgFileName(dto.getOrgFileName())
                .orgFileName(image.getOriginalFilename())
                .filePath("/images/stayRegImage/" + filePath)
                .build();

        reserveRepository.save(stay);
    }

    /**
     * 숙소 목록 조회
     * @return 숙소 목록
     */
    @Override
    public List<adminStayListResponse> getAll() {
        return reserveRepository.findAll().stream()
                .map(adminStayListResponse::from)
                .toList();
    }

    /**
     * 숙소 정보 조회(단건)
     * @param stayId 조회 대상 ID
     * @return 조회 정보
     */
    @Override
    public StayDetailResponse getStayDetail(Long stayId) {
        Stay stay = reserveRepository.findById(stayId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));
        return StayDetailResponse.from(stay);
    }

    @Transactional
    @Override
    public void updateStay(
            StayUpdateRequest dto,
            MultipartFile image) throws IOException {

        if (!image.isEmpty()) {
            //파일 삭제 후 저장
            FileUploadUtil.deleteFile(dto.getFilePath());
            String filePath = FileUploadUtil.saveFile(image, uploadDir);
            
            dto.setFilePath(filePath);
            dto.setOrgFileName(image.getOriginalFilename());
        }

        Stay stay = reserveRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));
        stay.update(dto);
    }

    /**
     * 숙소 삭제
     * @param stayId 삭제 대상 id
     */
    @Transactional
    @Override
    public void deleteStay(Long stayId) {
        Stay stay = reserveRepository.findById(stayId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));
        reserveRepository.delete(stay);
    }
}
