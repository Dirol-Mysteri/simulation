package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

public abstract class Entity {
    EntityType entityType;
    Coordinates coordinates;

    /**
     * Returns the visual representation (sprite) of the entity.
     *
     * @return a string representation of the sprite
     */

    public abstract String getSprite();

    public EntityType getEntityType() {
        return this.entityType;
    }

    public boolean isType(EntityType targetType) {
        return this.entityType == targetType;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
