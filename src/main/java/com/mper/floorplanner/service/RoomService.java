package com.mper.floorplanner.service;

import com.mper.floorplanner.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto create(RoomDto roomDto);

    RoomDto update(RoomDto roomDto);

    List<RoomDto> findAll();

    RoomDto findById(Long id);

    void deleteById(Long id);
}
