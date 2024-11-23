package org.example.utils;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Entity;

import java.util.Map;

public class Renderer {
    private final GameMap map;

    public Renderer(GameMap map) {
        this.map = map;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\u001b\143");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Не удалось очистить консоль.");
        }
    }


    public void render() {
        clearConsole();
        System.out.println();
        System.out.println();
        System.out.println();
        Map<Coordinates, Entity> entities = map.getEntities();
        for (int i = 0; i < this.map.getN(); i++) {
            for (int j = 0; j < this.map.getM(); j++) {
                Entity entity = entities.get(new Coordinates(i, j));
                String sprite = (entity != null) ? entity.getSprite() : "___";
                System.out.print(" " + sprite + " ");
            }
            System.out.println();
        }
        System.out.println("Нажмите клавишу \"P\", чтобы приостановить симуляцию");
    }
}
