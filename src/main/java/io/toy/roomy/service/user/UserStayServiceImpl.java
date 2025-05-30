package io.toy.roomy.service.user;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.dto.response.room.RoomDetailRecord;
import io.toy.roomy.dto.response.stay.StayDetailRecord;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.repository.StayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 숙소 Service
 */
@Service
public class UserStayServiceImpl implements UserStayService {

    private final StayRepository stayRepository;

    public UserStayServiceImpl(StayRepository reserveRepository) {
        this.stayRepository = reserveRepository;
    }

    /**
     * 숙소 목록 조회
     * @return 숙소 목록
     */
    @Override
    public List<StayListResponse> getStayListAll() {
        return stayRepository.findAll().stream()
                .map(StayListResponse::from)
                .toList();
    }

    @Override
    public StayDetailRecord getStayDetail(Long stayId) {
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new EntityNotFoundException("해당 숙소가 존재하지 않습니다."));

        return StayDetailRecord.from(stay);
    }

    /**
     * 숙소에 연결된 객실 상세조회
     * @param stayId 숙소 ID
     * @return List<객실 정보>
     */
    @Override
    public List<RoomDetailRecord> getRoomsByStayID(Long stayId) {
        // StayRepository를 통해 특정 Stay 객체를 조회
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new EntityNotFoundException("ID가" + stayId + "인 숙소를 찾을 수 없습니다."));

        // 이 Stay 객체에 속한 Room 리스트를 가져옵니다.
        List<Room> roomsOfThisStay = stay.getRooms();

        return roomsOfThisStay.stream()
                .map(RoomDetailRecord::from)
                .toList();
    }
}
