package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.RoomRequest;
import io.toy.roomy.dto.response.admin.adminRoomListResponse;
import io.toy.roomy.dto.response.admin.adminStayListResponse;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
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
    public List<adminRoomListResponse> getRoomList() {
        return roomRepository.findAll().stream()
                .map(adminRoomListResponse::from)
                .toList();
    }

    /**
     * 객실등록
     * @param dto
     * @return
     */
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
}
