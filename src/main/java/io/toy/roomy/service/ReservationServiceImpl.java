package io.toy.roomy.service;

import io.toy.roomy.domain.Accommodation;
import io.toy.roomy.dto.request.AccommodationRequest;
import io.toy.roomy.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reserveRepository;

    public ReservationServiceImpl(ReservationRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    /**
     * 객실 등록
     * @param dto
     * @return
     */
    @Override
    public Accommodation regAccommodation(AccommodationRequest dto) {
        Accommodation accommodation = Accommodation.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .type(dto.getType())
                .build();

        return reserveRepository.save(accommodation);
    }
}
