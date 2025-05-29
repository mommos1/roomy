package io.toy.roomy.service.admin;

import io.toy.roomy.common.FileUploadUtil;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.stay.StayRequest;
import io.toy.roomy.dto.request.stay.StayUpdateRequest;
import io.toy.roomy.dto.response.stay.StayDetailResponse;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.repository.StayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 */
@Service
public class AdminStayServiceImpl implements AdminStayService {

    private final StayRepository stayRepository;

    public AdminStayServiceImpl(StayRepository reserveRepository) {
        this.stayRepository = reserveRepository;
    }
    
    //숙소 대표이미지 저장 경로
    private final String uploadDir = "/images/stayRegImage";

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

        stayRepository.save(stay);
    }

    /**
     * 숙소 목록 조회
     * @return 숙소 목록
     */
    @Override
    public List<StayListResponse> getAll() {
        return stayRepository.findAll().stream()
                .map(StayListResponse::from)
                .toList();
    }

    /**
     * 숙소 정보 조회(단건)
     * @param stayId 조회 대상 ID
     * @return 조회 정보
     */
    @Override
    public StayDetailResponse getStayDetail(Long stayId) {
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));
        return StayDetailResponse.from(stay);
    }

    /**
     * 숙소 수정
     * @param dto 수정 정보
     * @param image 숙소 미리보기 이미지
     * @throws IOException 파일 등록 실패 시
     */
    @Transactional
    @Override
    public void updateStay(
            StayUpdateRequest dto,
            MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            //파일 삭제 후 저장
            FileUploadUtil.deleteFile(dto.getOrgFilePath());
            String filePath = FileUploadUtil.saveFile(image, uploadDir);
            
            dto.setFilePath(uploadDir + "/" + filePath);
            dto.setOrgFileName(image.getOriginalFilename());
        }

        Stay stay = stayRepository.findById(dto.getId())
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
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));

        FileUploadUtil.deleteFile(stay.getFilePath());
        stayRepository.delete(stay);
    }
}
