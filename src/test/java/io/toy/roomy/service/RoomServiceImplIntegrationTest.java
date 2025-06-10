package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceImplIntegrationTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StayRepository stayRepository;

    private Long preExistingStayId; // 테스트를 위한 Stay ID

    @BeforeEach
    void setUp() {
        // 테스트 실행 전, Room이 참조할 Stay를 실제 DB에 저장
        // @Transactional 덕분에 테스트 후 롤백됩니다.
        Stay testStay = Stay.builder()
                .name("통합 테스트 숙소")
                .build();
        Stay savedStay = stayRepository.save(testStay);
        preExistingStayId = savedStay.getId();
    }


    @Test
    @DisplayName("객실 등록 시 실제 DB에 저장되고 ID가 자동 생성되는지 확인")
    void regRoom_shouldSaveToDbAndGenerateId() {
        // given
        RoomRequest roomRequest = RoomRequest.builder()
                .name("통합 테스트 객실")
                .numberOfRooms(2)
                .pricePerNight(50000)
                .capacity(4)
                .description("통합 테스트용 객실입니다.")
                .stayId(preExistingStayId) // 실제 존재하는 Stay ID 사용
                .build();

        // when
        // roomService의 regRoom 메서드를 호출하면 내부적으로 roomRepository.save()가 호출되고
        // 실제 DB에 데이터가 저장됩니다.
        Room savedRoom = roomService.regRoom(roomRequest);

        // then
        // 1. 반환된 객체의 ID가 null이 아닌지 확인 (자동 생성 확인)
        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getId()).isNotNull(); // 가장 중요! ID 자동 생성 검증
        assertThat(savedRoom.getId()).isPositive(); // ID가 양수인지 확인 (일반적으로)

        // 2. 실제 DB에서 해당 ID로 조회하여 데이터가 존재하는지, 그리고 요청한 정보와 일치하는지 확인
        Optional<Room> foundRoomOptional = roomRepository.findById(savedRoom.getId());
        assertThat(foundRoomOptional).isPresent(); // 데이터가 DB에 존재하는지
        Room foundRoom = foundRoomOptional.get();

        assertThat(foundRoom.getName()).isEqualTo(roomRequest.getName());
        assertThat(foundRoom.getNumberOfRooms()).isEqualTo(roomRequest.getNumberOfRooms());
        // 다른 필드들도 모두 비교
        assertThat(foundRoom.getPricePerNight()).isEqualTo(roomRequest.getPricePerNight());
        assertThat(foundRoom.getCapacity()).isEqualTo(roomRequest.getCapacity());
        assertThat(foundRoom.getDescription()).isEqualTo(roomRequest.getDescription());
        assertThat(foundRoom.getStay().getId()).isEqualTo(roomRequest.getStayId()); // Stay 연관 관계도 확인
    }


}