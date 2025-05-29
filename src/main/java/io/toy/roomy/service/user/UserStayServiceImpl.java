package io.toy.roomy.service.user;

import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.repository.StayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 숙소 Service
 */
@Service
public class UserStayServiceImpl implements UserStayService {

    private final StayRepository reserveRepository;

    public UserStayServiceImpl(StayRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    /**
     * 숙소 목록 조회
     * @return 숙소 목록
     */
    @Override
    public List<StayListResponse> getStayListAll() {
        return reserveRepository.findAll().stream()
                .map(StayListResponse::from)
                .toList();
    }
}
