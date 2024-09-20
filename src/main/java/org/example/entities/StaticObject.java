package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class StaticObject extends Entity {

    private final String sprite;

    public StaticObject(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.STATIC_OBJECT;
        ArrayList<String> objectSprites = new ArrayList<String>(List.of("\uD83E\uDEA8", "\uD83E\uDEB5", "\uD83C\uDF33"));
        this.sprite = objectSprites.get((int) Math.round(Math.random() * 2));
    }

    public String getSprite() {
        return sprite;
    }
}
