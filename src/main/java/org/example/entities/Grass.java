package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

public class Grass extends Entity {
    private boolean isEaten;

    public Grass(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.RESOURCE;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public String getSprite() {
        return "\uD83C\uDF31";
    }
}
