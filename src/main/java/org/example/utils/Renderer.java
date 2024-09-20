package org.example.utils;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Entity;

public class Renderer {
    private final GameMap map;

    public Renderer(GameMap map) {
        this.map = map;
    }

    public void render() {
        for (int i = 0; i < this.map.getN(); i++) {
            for (int j = 0; j < this.map.getM(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Entity entity = this.map.getEntities().get(coordinates);
                String sprite = (entity != null) ? entity.getSprite() : ".";
                System.out.print(" " + sprite + " ");
            }
            System.out.println();
        }
    }
}
