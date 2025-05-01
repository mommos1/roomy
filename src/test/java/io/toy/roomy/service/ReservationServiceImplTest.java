package io.toy.roomy.service;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.domain.type.StayType;
import io.toy.roomy.dto.request.StayRequest;
import io.toy.roomy.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationServiceImplTest {

    @Autowired
    private AdminStayService reservationService;
    @Autowired
    private ReservationRepository reserveRepository;

    @Test
    @DisplayName("숙소업체등록")
    void regAccommodation() {
        //Given
        StayRequest dto = StayRequest.builder()
                .name("테스트호텔")
                .type(StayType.HOTEL)
                .location("서울특별시")
                .build();
        //When
        Stay accommodation = reservationService.regStay(dto);
        //Then
        assertThat(reserveRepository.findByName(accommodation.getName())).isPresent();
    }
}