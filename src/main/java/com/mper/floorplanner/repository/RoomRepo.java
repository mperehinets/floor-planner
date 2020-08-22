package com.mper.floorplanner.repository;

import com.mper.floorplanner.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
