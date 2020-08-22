package com.mper.floorplanner.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "angles")
public class Angle extends BaseEntity {

    @Column(name = "x")
    private Integer x;

    @Column(name = "y")
    private Integer y;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room")
    private Room room;

    public Angle() {
    }

    public Angle(Integer x, Integer y) {
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angle)) return false;
        Angle angle = (Angle) o;
        return x.equals(angle.x) &&
                y.equals(angle.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Angle{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
