package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.response.room.RoomRecord;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoomServiceImplIntegrationTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StayRepository stayRepository;

    private Long preExistingStayId; // 테스트를 위한 Stay ID
    private List<Room> preExistingRooms;

    @BeforeEach
    void setUp() {
        Stay stay = Stay.builder()
                .name("통합 테스트 숙소")
                .build();
        Stay savedStay = stayRepository.save(stay);
        preExistingStayId = savedStay.getId();

        Room room = Room.builder()
                .name("테스트 객실1")
                .numberOfRooms(3)
                .pricePerNight(30000)
                .capacity(2)
                .description("티비 냉장고 스타일러")
                .stay(stay)
                .build();

        Room room2 = Room.builder()
                .name("테스트 객실2")
                .numberOfRooms(4)
                .pricePerNight(40000)
                .capacity(3)
                .description("티비 냉장고 스타일러2")
                .stay(stay)
                .build();
        preExistingRooms = roomRepository.saveAll(Arrays.asList(room, room2));
    }

    @Test
    @DisplayName("객실 목록 조회 - 성공")
    void getRoomList_shouldReturnRoomList() {
        //given
        //when
        List<RoomRecord> roomList = roomService.getRoomList(preExistingStayId);

        //then
        assertThat(roomList).isNotNull();
        assertThat(roomList).isNotEmpty();
        assertThat(roomList.size()).isEqualTo(2);
        assertThat(roomList.getFirst().name()).isEqualTo(preExistingRooms.getFirst().getName());
    }

//    @Test
//    @DisplayName("객실 목록 조회 - 실패")

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
        Room savedRoom = roomService.regRoom(roomRequest);

        // then
        // 반환된 객체의 ID가 null이 아닌지 확인 (자동 생성 확인)
        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getId()).isNotNull(); // ID 자동 생성 검증
        assertThat(savedRoom.getId()).isPositive(); // ID가 양수인지 확인

        // 실제 DB에서 해당 ID로 조회하여 데이터가 존재하는지, 그리고 요청한 정보와 일치하는지 확인
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