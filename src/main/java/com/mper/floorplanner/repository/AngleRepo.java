package com.mper.floorplanner.repository;

import com.mper.floorplanner.entity.Angle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface AngleRepo extends JpaRepository<Angle, Long> {

    @Transactional
    @Modifying
    @Query("delete from Angle a where a.room.id = :roomId")
    void deleteByRoomId(@Param("roomId") Long roomId);
}
