package com.mper.floorplanner.dto.mapper;

import com.mper.floorplanner.dto.AngleDto;
import com.mper.floorplanner.entity.Angle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AngleMapper {

    Angle toEntity(AngleDto dto);

    AngleDto toDto(Angle entity);

}
