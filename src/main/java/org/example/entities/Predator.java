package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.CreatureType;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.ArrayList;
import java.util.List;

public class Predator extends Creature {
    private final String sprite;

    public Predator(int positionN, int positionM) {
        super(positionN, positionM, EntityType.PREDATOR);
        ArrayList<String> predatorSprites = new ArrayList<>(List.of("\uD83D\uDC0A", "\uD83E\uDD81", "\uD83D\uDC3A"));
        this.sprite = predatorSprites.get((int) Math.round(Math.random() * 2));
    }

    public String getSprite() {
        return sprite;
    }
}
