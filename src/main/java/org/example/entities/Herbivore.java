package org.example.entities;

import org.example.enums.CreatureType;
import org.example.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Herbivore extends Creature {
    private final String sprite;

    public Herbivore(int positionN, int positionM) {
        super(positionN, positionM, EntityType.ANIMAL);
        this.typeOfCreature = CreatureType.HERBIVORE;
        ArrayList<String> herbivoreSprites = new ArrayList<String>(List.of("\uD83D\uDC37", "\uD83D\uDC14", "\uD83E\uDD86"));
        this.sprite = herbivoreSprites.get((int) Math.round(Math.random() * 2));
    }

    @Override
    void makeMove() {

    }

    public String getSprite() {
        return sprite;
    }
}
