package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

public class FreeSpace extends Entity {

    public FreeSpace(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.FREE_SPACE;
    }

    public String getSprite() {
        return "___";
    }
}
