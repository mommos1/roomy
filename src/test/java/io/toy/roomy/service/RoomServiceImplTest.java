package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.response.room.RoomRecord;
import io.toy.roomy.mapper.RoomMapper;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Mockito 확장 기능 사용
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private StayRepository stayRepository;
    @Mock
    private RoomMapper roomMapper;

    // 위의 ExtendWith의 확장기능덕분에 자동으로 주입받아 간결한 테스트코드 작성 가능
    @InjectMocks
    private RoomServiceImpl roomService;

    private RoomRecord roomRecord;
    private RoomRequest roomRequest;
    private Stay mockStay;

    @BeforeEach
    void setUp() {
        Long stayId = 1L;
        Long roomId = 2L;

        // 테스트용 객실 정보
        roomRequest = RoomRequest.builder()
                .id(roomId)
                .name("테스트 객실")
                .numberOfRooms(3)
                .pricePerNight(30000)
                .capacity(2)
                .description("TV 냉장고 스타일러")
                .stayId(stayId)
                .build();

        //숙소 조회 시 사용
        mockStay = Stay.builder()
                .id(stayId)
                .name("테스트숙소")
                .build();
        given(stayRepository.findById(stayId)).willReturn(Optional.of(mockStay));
    }

    @Test
    @DisplayName("객실 등록 성공")
    void regRoom_shouldSaveStay() {
        // given
        Room roomReturnedByMapper = Room.builder()
                .id(roomRequest.getId())
                .name(roomRequest.getName())
                .numberOfRooms(roomRequest.getNumberOfRooms())
                .pricePerNight(roomRequest.getPricePerNight())
                .capacity(roomRequest.getCapacity())
                .description(roomRequest.getDescription())
                .build();
        given(roomMapper.toEntity(roomRequest)).willReturn(roomReturnedByMapper);

        // when
        roomService.regRoom(roomRequest);

        // then
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        then(roomRepository).should(times(1)).save(roomArgumentCaptor.capture());

        Room savingRoom = roomArgumentCaptor.getValue();
        //assertThat(roomRequest.getId()).isNotNull();
        assertThat(roomRequest.getName()).isEqualTo(savingRoom.getName());
        assertThat(roomRequest.getNumberOfRooms()).isEqualTo(savingRoom.getNumberOfRooms());
        assertThat(roomRequest.getPricePerNight()).isEqualTo(savingRoom.getPricePerNight());
        assertThat(roomRequest.getCapacity()).isEqualTo(savingRoom.getCapacity());
        assertThat(roomRequest.getDescription()).isEqualTo(savingRoom.getDescription());
        
        assertThat(savingRoom.getStay()).isNotNull();
        assertThat(savingRoom.getStay().getId()).isEqualTo(mockStay.getId());
    }

    //updateStay_whenStayNotExists_shouldThrowException
    @Test
    @DisplayName("객실 등록 실패 - ")
    void regRoom_whenStayNotExists_shouldNotSaveRoom() {

    }
}