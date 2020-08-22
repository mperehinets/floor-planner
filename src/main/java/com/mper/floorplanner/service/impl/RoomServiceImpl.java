package com.mper.floorplanner.service.impl;

import com.mper.floorplanner.dto.AngleDto;
import com.mper.floorplanner.dto.RoomDto;
import com.mper.floorplanner.dto.mapper.RoomMapper;
import com.mper.floorplanner.exception.PointsNotGoClockwiseException;
import com.mper.floorplanner.exception.RoomHasNoRightAnglesException;
import com.mper.floorplanner.exception.RoomNotFoundException;
import com.mper.floorplanner.repository.AngleRepo;
import com.mper.floorplanner.repository.RoomRepo;
import com.mper.floorplanner.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomRepo roomRepo;
    private final RoomMapper roomMapper;
    private final AngleRepo angleRepo;

    public RoomServiceImpl(RoomRepo roomRepo, RoomMapper roomMapper, AngleRepo angleRepo) {
        this.roomRepo = roomRepo;
        this.roomMapper = roomMapper;
        this.angleRepo = angleRepo;
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        tryToBuildFloor(roomDto);
        RoomDto result = roomMapper.toDto(roomRepo.save(roomMapper.toEntity(roomDto)));
        LOGGER.info("IN create - room: {} successfully created", result);
        return result;
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        RoomDto foundRoomDto = findById(roomDto.getId());
        if (!roomDto.getRoom().equals(foundRoomDto.getRoom())) {
            tryToBuildFloor(roomDto);
            angleRepo.deleteByRoomId(roomDto.getId());
            RoomDto result = roomMapper.toDto(roomRepo.save(roomMapper.toEntity(roomDto)));
            LOGGER.info("IN update - room: {} successfully updated", result);
            return result;
        }
        return roomDto;
    }

    @Override
    public List<RoomDto> findAll() {
        List<RoomDto> result = roomRepo.findAll().stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
        LOGGER.info("IN findAll - {} rooms found", result.size());
        return result;
    }

    @Override
    public RoomDto findById(Long id) {
        RoomDto result = roomMapper.toDto(roomRepo.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found by id: " + id)));
        LOGGER.info("IN findById - rooms: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        roomRepo.deleteById(id);
        LOGGER.info("IN deleteById - room with id: {} successfully deleted", id);
    }

    private void tryToBuildFloor(RoomDto roomDto) {
        if (!isClockwise(roomDto.getRoom())) {
            throw new PointsNotGoClockwiseException("Angles don't go clockwise");
        }
        isRightAngle(roomDto.getRoom());
    }

    private Boolean isClockwise(List<AngleDto> anglesDto) {
        AngleDto[] arr = anglesDto.toArray(new AngleDto[0]);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sum += (arr[0].getX() - arr[i].getX()) * (arr[0].getY() + arr[i].getY());
            } else {
                sum += (arr[i + 1].getX() - arr[i].getX()) * (arr[i + 1].getY() + arr[i].getY());
            }
        }
        return sum >= 0;
    }

    private void isRightAngle(List<AngleDto> anglesDto) {
        AngleDto[] arr = anglesDto.toArray(new AngleDto[0]);
        for (int i = 0; i < arr.length - 1; i++) {
            if (i != arr.length - 2) {
                if (!isRightAngle(arr[i], arr[i + 1], arr[i + 2])) {
                    throwNotRightAngleException(arr[i], arr[i + 1], arr[i + 2]);
                }
            } else if (!isRightAngle(arr[i], arr[i + 1], arr[0])) {
                throwNotRightAngleException(arr[i], arr[i + 1], arr[0]);
            }
        }
    }

    public Boolean isRightAngle(AngleDto p1, AngleDto p2, AngleDto p3) {
        AngleDto v1 = new AngleDto(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        AngleDto v2 = new AngleDto(p3.getX() - p2.getX(), p3.getY() - p2.getY());
        int scalarProduct = v1.getX() * v2.getX() + v1.getY() * v2.getY();
        double v1Length = sqrt(pow(v1.getX(), 2) + pow(v1.getY(), 2));
        double v2Length = sqrt(pow(v2.getX(), 2) + pow(v2.getY(), 2));
        double result = scalarProduct / (v1Length * v2Length);
        return result == 0;
    }

    private void throwNotRightAngleException(AngleDto p1, AngleDto p2, AngleDto p3) {
        throw new RoomHasNoRightAnglesException(String.format("Room has no right angle: [%s,%s,%s]", p1, p2, p3));
    }
}
