package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

public abstract class Entity {
    EntityType entityType;
    Coordinates coordinates;

    public abstract String getSprite();
}
