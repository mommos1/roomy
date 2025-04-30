package io.toy.roomy.service;

import io.toy.roomy.domain.Accommodation;
import io.toy.roomy.dto.request.AccommodationRequest;
import io.toy.roomy.repository.ReserveRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReserveRepository reserveRepository;

    public ReservationServiceImpl(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public Accommodation registerRoom(AccommodationRequest dto) {
        Accommodation accommodation = Accommodation.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .type(dto.getType())
                .build();

        return reserveRepository.save(accommodation);
    }
}
