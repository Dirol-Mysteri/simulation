package org.example.entities;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.enums.EntityType;
import org.example.utils.FindPathWithBFS;

import java.util.ArrayList;
import java.util.List;

public class Herbivore extends Creature {
    private static final int DAMAGE = 2;
    private final String sprite;

    public Herbivore(int positionN, int positionM) {
        super(positionN, positionM, EntityType.HERBIVORE);
        ArrayList<String> herbivoreSprites = new ArrayList<String>(List.of("\uD83D\uDC37", "\uD83D\uDC14", "\uD83E\uDD86"));
        this.sprite = herbivoreSprites.get((int) Math.round(Math.random() * 2));
        this.speed = 2;
        this.hp = 10;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        List<Coordinates> path = FindPathWithBFS.findPath(gameMap.getEntities(), this, EntityType.RESOURCE);
        for (Coordinates coordinate : path) {
            System.out.println(gameMap.getEntities().get(coordinate).getEntityType());
        }
//        System.out.println(path);
    }

    public String getSprite() {
        return sprite;
    }

    public void takeDamage() {
        this.hp = this.hp - DAMAGE;
        System.out.println("Herbivore damage message should be here");
    }
}
