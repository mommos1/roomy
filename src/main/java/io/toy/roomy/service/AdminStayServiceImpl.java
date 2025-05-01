package io.toy.roomy.service;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminStayServiceImpl implements AdminStayService {

    private final ReservationRepository reserveRepository;

    public AdminStayServiceImpl(ReservationRepository reserveRepository) {
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
                .build();

        return reserveRepository.save(accommodation);
    }
}
