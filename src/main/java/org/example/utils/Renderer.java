package org.example.utils;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Entity;
import org.w3c.dom.ls.LSOutput;

public class Renderer {
    private final GameMap map;

    public Renderer(GameMap map) {
        this.map = map;
    }

    public static void clearConsole() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\u001b\143");
        System.out.flush();
    }

    public void render() {
        clearConsole();
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < this.map.getN(); i++) {
            for (int j = 0; j < this.map.getM(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Entity entity = this.map.getEntities().get(coordinates);
                String sprite = (entity != null) ? entity.getSprite() : "___";
                System.out.print(" " + sprite + " ");
            }
            System.out.println();
        }
    }
}
