package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.RoomRequest;
import io.toy.roomy.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminRoomServiceImpl implements AdminRoomService {

    private final RoomRepository roomRepository;

    public AdminRoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * 객실등록
     * @param dto
     * @return
     */
    @Override
    public Room regRoom(RoomRequest dto) {
        Room room = Room.builder()
                .name(dto.getName())
                .pricePerNight(dto.getPricePerNight())
                .capacity(dto.getCapacity())
                .description(dto.getDescription())
                .build();

        return roomRepository.save(room);
    }
}
