package com.mper.floorplanner.dto;

import com.mper.floorplanner.dto.transfer.OnCreate;
import com.mper.floorplanner.dto.transfer.OnUpdate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class AngleDto {

    @Min(groups = {OnCreate.class, OnUpdate.class},
            value = 0,
            message = "Min value for X is {value}")
    @Max(groups = {OnCreate.class, OnUpdate.class},
            value = 100,
            message = "Max value for X is {value}")
    private Integer x;

    @Min(groups = {OnCreate.class, OnUpdate.class},
            value = 0,
            message = "Min value for Y is {value}")
    @Max(groups = {OnCreate.class, OnUpdate.class},
            value = 100,
            message = "Max value for Y is {value}")
    private Integer y;

    public AngleDto() {
    }

    public AngleDto(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AngleDto)) return false;
        AngleDto angleDto = (AngleDto) o;
        return x.equals(angleDto.x) &&
                y.equals(angleDto.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
