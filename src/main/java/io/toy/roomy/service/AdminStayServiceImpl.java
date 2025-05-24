package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
import io.toy.roomy.repository.StayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminStayServiceImpl implements AdminStayService {

    private final StayRepository reserveRepository;

    public AdminStayServiceImpl(StayRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    /**
     * 숙소 등록 (
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public Stay regStay(StayRequest dto) {
        Stay accommodation = Stay.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .type(dto.getType())
                .filePath(dto.getFilePath())
                .build();

        return reserveRepository.save(accommodation);
    }

    @Transactional
    @Override
    public List<adminStayListResponse> getAll() {
        return reserveRepository.findAll().stream()
                .map(adminStayListResponse::from)
                .toList();
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
