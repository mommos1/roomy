package io.toy.roomy.service;

import io.toy.roomy.dto.response.user.userStayListResponse;
import io.toy.roomy.repository.StayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStayServiceImpl implements UserStayService {

    private final StayRepository reserveRepository;

    public UserStayServiceImpl(StayRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public List<userStayListResponse> getStayListAll() {
        return reserveRepository.findAll().stream()
                .map(userStayListResponse::from)
                .toList();
    }
}
