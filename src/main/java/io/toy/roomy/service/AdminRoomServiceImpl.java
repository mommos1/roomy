package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.RoomRequest;
import io.toy.roomy.dto.request.RoomUpdateRequest;
import io.toy.roomy.dto.response.admin.RoomDetailResponse;
import io.toy.roomy.dto.response.admin.adminRoomListResponse;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
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

    @Override
    public List<adminRoomListResponse> getRoomList(Long stayId) {
        return roomRepository.findByStayId(stayId).stream()
                .map(adminRoomListResponse::from)
                .toList();
    }

    /**
     * 객실 정보 조회(단건)
     * @param roomId 조회 대상 ID
     * @return 조회 정보
     */
    @Override
    public RoomDetailResponse getRoomDetail(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));
        return RoomDetailResponse.from(room);
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

    @Transactional
    @Override
    public void updateRoom(Long roomId, RoomUpdateRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));

        room.update(request);
    }
}
