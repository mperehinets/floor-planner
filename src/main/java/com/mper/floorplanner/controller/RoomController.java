package com.mper.floorplanner.controller;

import com.mper.floorplanner.dto.RoomDto;
import com.mper.floorplanner.dto.transfer.OnCreate;
import com.mper.floorplanner.dto.transfer.OnUpdate;
import com.mper.floorplanner.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDto create(@Validated(OnCreate.class) @RequestBody RoomDto roomDto) {
        return roomService.create(roomDto);
    }

    @PutMapping("/{id}")
    public RoomDto update(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody RoomDto roomDto) {
        roomDto.setId(id);
        return roomService.update(roomDto);
    }

    @GetMapping
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
    }
}
