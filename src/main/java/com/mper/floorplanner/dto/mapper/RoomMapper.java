package com.mper.floorplanner.dto.mapper;

import com.mper.floorplanner.dto.RoomDto;
import com.mper.floorplanner.entity.Room;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "room", target = "angles")
    Room toEntity(RoomDto dto);

    @Mapping(source = "angles", target = "room")
    RoomDto toDto(Room entity);

    @AfterMapping
    default void bindingAnglesToRoom(@MappingTarget Room room) {
        room.getAngles().forEach(angle -> angle.setRoom(room));
    }
}
