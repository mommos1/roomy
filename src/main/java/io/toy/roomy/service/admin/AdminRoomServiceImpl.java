package io.toy.roomy.service.admin;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.request.room.RoomUpdateRequest;
import io.toy.roomy.dto.response.room.RoomDetailRecord;
import io.toy.roomy.dto.response.room.AdminRoomListResponse;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoomServiceImpl implements AdminRoomService {

    private final RoomRepository roomRepository;
    private final StayRepository stayRepository;

    public AdminRoomServiceImpl(RoomRepository roomRepository, StayRepository stayRepository) {
        this.roomRepository = roomRepository;
        this.stayRepository = stayRepository;
    }

    /**
     * 객실 목록 조회
     * @param stayId 조회 대상 ID
     * @return 객실 목록
     */
    @Override
    public List<AdminRoomListResponse> getRoomList(Long stayId) {
        return roomRepository.findByStayId(stayId).stream()
                .map(AdminRoomListResponse::from)
                .toList();
    }

    /**
     * 객실 정보 조회(단건)
     * @param roomId 조회 대상 ID
     * @return 조회 정보
     */
    @Override
    public RoomDetailRecord getRoomDetail(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));
        return RoomDetailRecord.from(room);
    }

    /**
     * 객실등록
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public Room regRoom(RoomRequest dto) {
        Stay stay = stayRepository.findById(dto.getStayId()).orElseThrow();
        Room room = Room.builder()
                .name(dto.getName())
                .pricePerNight(dto.getPricePerNight())
                .capacity(dto.getCapacity())
                .description(dto.getDescription())
                .stay(stay)
                .build();

        return roomRepository.save(room);
    }

    /**
     * 객실 삭제
     * @param roomId 삭제 대상 id
     */
    @Transactional
    @Override
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));
        roomRepository.delete(room);
    }

    /**
     * 객실 수정
     * @param roomId 수정 대상 ID
     * @param dto 객실 정보
     */
    @Transactional
    @Override
    public void updateRoom(Long roomId, RoomUpdateRequest dto) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));

        room.update(dto);
    }
}
