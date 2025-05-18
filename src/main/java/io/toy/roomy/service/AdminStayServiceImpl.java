package io.toy.roomy.service;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
import io.toy.roomy.repository.StayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminStayServiceImpl implements AdminStayService {

    private final StayRepository reserveRepository;

    public AdminStayServiceImpl(StayRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    /**
     * 객실 등록 (
     * @param dto
     * @return
     */
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

    @Override
    public List<adminStayListResponse> getAll() {
        return reserveRepository.findAll().stream()
                .map(adminStayListResponse::from)
                .toList();
    }
}
