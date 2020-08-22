package com.mper.floorplanner.dto;

import com.mper.floorplanner.dto.transfer.OnCreate;
import com.mper.floorplanner.dto.transfer.OnUpdate;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDto extends BaseDto {

    @Valid
    @Size(groups = {OnCreate.class, OnUpdate.class},
            min = 4,
            message = "At least 4 corners must be present")
    private List<AngleDto> room = new ArrayList<>();

    public RoomDto() {
    }

    public RoomDto(Long id, List<AngleDto> room) {
        super(id);
        this.room = room;
    }

    public List<AngleDto> getRoom() {
        return room;
    }

    public void setRoom(List<AngleDto> room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomDto)) return false;
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(room, roomDto.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "room=" + room +
                '}';
    }
}
