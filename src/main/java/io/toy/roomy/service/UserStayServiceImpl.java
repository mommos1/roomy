package io.toy.roomy.service;

import io.toy.roomy.dto.response.userStayListResponse;
import io.toy.roomy.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStayServiceImpl implements UserStayService {

    private final ReservationRepository reserveRepository;

    public UserStayServiceImpl(ReservationRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public List<userStayListResponse> getStayListAll() {
        return reserveRepository.findAll().stream()
                .map(userStayListResponse::from)
                .toList();
    }
}
