package io.toy.roomy.mapper; // 매퍼는 보통 'mapper' 패키지에 위치시킵니다.

import io.toy.roomy.domain.Room;
import io.toy.roomy.dto.request.room.RoomRequest; // 요청 DTO
import io.toy.roomy.dto.response.room.RoomRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    //RoomRequest -> Room
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stay", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Room toEntity(RoomRequest roomRequest);

    //
//    @Mapping(source = "stay.id", target = "stayId") // RoomResponse에 stayId 필드가 있다면 매핑
//    @Mapping(source = "stay.name", target = "stayName") // RoomResponse에 stayName 필드가 있다면 매핑
    RoomRecord toRoomResponse(Room room);

    // --- 엔티티 리스트 (List<Room>) -> 응답 DTO 리스트 (List<RoomResponse>) 변환 ---
    List<RoomRecord> toRoomResponseList(List<Room> rooms);

    // --- 엔티티 업데이트 (RoomRequest -> Room) ---
    // RoomRequest DTO의 내용으로 기존 Room 엔티티를 업데이트합니다.
    // @MappingTarget을 사용하여 기존 객체를 수정합니다.
    @Mapping(target = "id", ignore = true) // ID는 업데이트 대상이 아님
    @Mapping(target = "stay", ignore = true) // Stay 연관관계는 업데이트 대상이 아님
    @Mapping(target = "reservations", ignore = true) // reservations 필드 무시
    void updateRoomFromRequest(RoomRequest roomRequest, @MappingTarget Room room);
}