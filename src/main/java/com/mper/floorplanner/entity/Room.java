package com.mper.floorplanner.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Angle> angles = new ArrayList<>();

    public Room() {
    }

    public Room(List<Angle> angles) {
        this.angles = angles;
    }

    public List<Angle> getAngles() {
        return angles;
    }

    public void setAngles(List<Angle> angles) {
        this.angles = angles;
    }

    public void addAngle(Angle angle) {
        angle.setRoom(this);
        angles.add(angle);
    }

    public void removeAngle(Angle angle) {
        angles.remove(angle);
        angle.setRoom(null);
    }

    @Override
    public String toString() {
        return "Room{" +
                "angles=" + angles +
                '}';
    }
}
