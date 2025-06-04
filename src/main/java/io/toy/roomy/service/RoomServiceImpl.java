package io.toy.roomy.service;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.request.room.RoomRequest;
import io.toy.roomy.dto.response.room.RoomRecord;
import io.toy.roomy.mapper.RoomMapper;
import io.toy.roomy.repository.RoomRepository;
import io.toy.roomy.repository.StayRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final StayRepository stayRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, StayRepository stayRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.stayRepository = stayRepository;
        this.roomMapper = roomMapper;
    }

    /**
     * 객실 목록 조회
     * @param stayId 조회 대상 ID
     * @return 객실 목록
     */
    @Override
    public List<RoomRecord> getRoomList(Long stayId) {
        List<Room> rooms = roomRepository.findByStayId(stayId);
        return roomMapper.toRoomResponseList(rooms);
    }

    /**
     * 객실 정보 조회(단건)
     * @param roomId 조회 대상 ID
     * @return 조회 정보
     */
    @Override
    public RoomRecord getRoomDetail(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("해당 객실이 존재하지 않습니다."));

        return roomMapper.toRoomResponse(room);
        //return RoomRecord.from(room);
    }

    /**
     * 객실등록
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public Room regRoom(RoomRequest dto) {
        Stay stay = stayRepository.findById(dto.getStayId())
                .orElseThrow(() -> new EntityNotFoundException("해당 숙소가 존재하지 않습니다."));

        Room room = roomMapper.toEntity(dto);
        room.setStay(stay);

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
    public void updateRoom(Long roomId, RoomRequest dto) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 객실이 존재하지 않습니다."));

        room.update(dto);
    }
}
