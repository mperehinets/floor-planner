package com.mper.floorplanner.dto;

import com.mper.floorplanner.dto.transfer.OnCreate;
import com.mper.floorplanner.dto.transfer.OnUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public abstract class BaseDto {

    @Null(groups = {OnCreate.class}, message = "Id is auto generation")
    @NotNull(groups = {OnUpdate.class}, message = "Id can't be null")
    private Long id;

    public BaseDto() {

    }

    public BaseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
