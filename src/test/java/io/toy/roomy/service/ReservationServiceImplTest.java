package io.toy.roomy.service;

import io.toy.roomy.domain.Accommodation;
import io.toy.roomy.domain.type.AccommodationType;
import io.toy.roomy.dto.request.AccommodationRequest;
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
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reserveRepository;

    @Test
    @DisplayName("숙소업체등록")
    void regAccommodation() {
        //Given
        AccommodationRequest dto = AccommodationRequest.builder()
                .name("테스트호텔")
                .type(AccommodationType.HOTEL)
                .location("서울특별시")
                .build();
        //When
        Accommodation accommodation = reservationService.regAccommodation(dto);
        //Then
        assertThat(reserveRepository.findByName(accommodation.getName())).isPresent();
    }
}