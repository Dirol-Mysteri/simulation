package org.example.entities;

import org.example.Coordinates;
import org.example.enums.EntityType;

import java.util.List;
import java.util.Random;

public class StaticObject extends Entity {

    private final String sprite;

    public StaticObject(int positionN, int positionM) {
        this.coordinates = new Coordinates(positionN, positionM);
        this.entityType = EntityType.STATIC_OBJECT;
        List<String> objectSprites = List.of("\uD83E\uDEA8", "\uD83E\uDEB5", "\uD83C\uDF33");
        this.sprite = objectSprites.get(new Random().nextInt(objectSprites.size()));
    }

    public String getSprite() {
        return sprite;
    }
}
