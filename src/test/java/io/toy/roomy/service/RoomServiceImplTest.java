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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private Room room;
    private Room room2;
    private RoomRecord roomRecord;
    private RoomRecord roomRecord2;

    private Stay stay;

    private RoomRequest roomRequest;

    private final Long stayId = 1L;

    private final Long roomId = 2L;
    private final Long room2Id = 3L;

    @BeforeEach
    void setUp() {

        // 테스트용 객실 정보
        roomRequest = RoomRequest.builder()
                .id(roomId)
                .name("테스트 객실")
                .stayId(stayId)
                .build();

        // 숙소 조회 시 사용
        stay = Stay.builder()
                .id(stayId)
                .name("테스트숙소")
                .build();

        // 객실 조회 시 사용
        room = Room.builder()
                .id(roomId)
                .name(roomRequest.getName())
                .stay(stay)
                .build();

        // 객실 리스트 조회 시 사용
        room2 = Room.builder()
                .id(room2Id)
                .name("두번째 객실")
                .stay(stay)
                .build();

        // RoomRecord
        roomRecord = RoomRecord.builder()
                .id(room.getId())
                .name(room.getName())
                .build();

        roomRecord2 = RoomRecord.builder()
                .id(room2.getId())
                .name(room2.getName())
                .build();
    }

    @Test
    @DisplayName("객실 목록 조회 성공")
    void getRoomList_shouldReturnRoomList() {
        // given
        List<Room> roomList = Arrays.asList(room, room2);
        given(roomRepository.findByStayId(stayId)).willReturn(roomList);

        List<RoomRecord> roomRecordList = Arrays.asList(roomRecord, roomRecord2);
        given(roomMapper.toRoomResponseList(roomList)).willReturn(roomRecordList);

        // when
        List<RoomRecord> result = roomService.getRoomList(stayId);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.getFirst().id()).isEqualTo(roomRecord.id());
        assertThat(result.getFirst().name()).isEqualTo(roomRecord.name());

        assertThat(result.getLast().id()).isEqualTo(roomRecord2.id());
        assertThat(result.getLast().name()).isEqualTo(roomRecord2.name());

        verify(roomRepository).findByStayId(stayId);
        verify(roomMapper).toRoomResponseList(roomList);
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
        given(stayRepository.findById(stayId)).willReturn(Optional.of(stay));

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
        assertThat(savingRoom.getStay().getId()).isEqualTo(stay.getId());
    }

    @Test
    @DisplayName("객실 등록 실패 - ")
    void regRoom_whenStayNotExists_shouldNotSaveRoom() {

    }
}