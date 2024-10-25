package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

public abstract class Entity {
    EntityType entityType;
    Coordinates coordinates;

    public abstract String getSprite();

    public EntityType getEntityType() {
        return entityType;
    }

    public boolean isType(EntityType targetType) {
        return this.getEntityType().equals(targetType);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    boolean isNeighbor(Coordinates target) {
        int dn = Math.abs(coordinates.getN() - target.getN());
        int dm = Math.abs(coordinates.getM() - target.getM());
        return dn <= 1 && dm <= 1;
    }

}
