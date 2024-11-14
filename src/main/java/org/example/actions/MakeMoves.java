package org.example.actions;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.EntityType;

import java.util.*;

public class MakeMoves implements Action {
    private final GameMap gameMap;

    public MakeMoves(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    // Working example

//    @Override
//    public void execute() {
//        HashSet<Entity> executed = new HashSet<>(); // Prevents repeated executions
//        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
//
//        for (int i = 0; i < this.gameMap.getN(); i++) {
//            for (int j = 0; j < this.gameMap.getM(); j++) {
//                Coordinates coordinates = new Coordinates(i, j);
//                if (entities.containsKey(coordinates)) {
//                    Entity entity = entities.get(coordinates);
//                    if (entity.getEntityType() == EntityType.PREDATOR || entity.getEntityType() == EntityType.HERBIVORE) {
//                        if (executed.contains(entity)) {
//                            continue;
//                        }
//                        ((Creature) entity).makeMove(this.gameMap);
//                        executed.add(entity);
//                    }
//                }
//            }
//        }
//    }

    // Test example

    @Override
    public void execute() {
        HashSet<Entity> executed = new HashSet<>(); // Prevents repeated executions
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        Set<Map.Entry<Coordinates, Entity>> entries = new HashSet<>(entities.entrySet());

        for (Map.Entry<Coordinates, Entity> entry : entries) {
            Coordinates coordinates = new Coordinates(entry.getKey());
            if (entities.containsKey(coordinates)) {
                Entity entity = entities.get(coordinates);
                if (entity.getEntityType() == EntityType.PREDATOR || entity.getEntityType() == EntityType.HERBIVORE) {
                    if (executed.contains(entity)) {
                        continue;
                    }
                    ((Creature) entity).makeMove(this.gameMap);
                    executed.add(entity);
                }
            }
        }
    }

}
