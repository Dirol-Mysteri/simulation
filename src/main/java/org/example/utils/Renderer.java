package org.example.utils;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Entity;

public class Renderer {
    private final GameMap map;

    public Renderer(GameMap map) {
        this.map = map;
    }

    public static void clearConsole() {
//        System.out.println("Attempting to clear console...");
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//        System.out.println("Console cleared.");

//        for (int i = 0; i < 50; i++) {
//            System.out.println();
//        }

        System.out.println("Attempting to clear console...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\u001b\143");
        System.out.flush();
        System.out.println("Console cleared.");
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
