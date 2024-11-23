package org.example.actions;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.ActionType;
import org.example.enums.EntityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakeMoves extends Action {
    private final GameMap gameMap;

    public MakeMoves(GameMap gameMap) {
        super(ActionType.TURN_ACTION);
        this.gameMap = gameMap;
    }

//    @Override
//    public void execute() {
//        HashSet<Entity> executed = new HashSet<>(); // Prevents repeated executions
//        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
//        Set<Map.Entry<Coordinates, Entity>> entries = new HashSet<>(entities.entrySet());
//
//        for (Map.Entry<Coordinates, Entity> entry : entries) {
////            Coordinates coordinates = new Coordinates(entry.getKey());
//            Coordinates coordinates = entry.getKey();
//            if (entities.containsKey(coordinates)) {
//                Entity entity = entities.get(coordinates);
//                if (entity.getEntityType() == EntityType.PREDATOR || entity.getEntityType() == EntityType.HERBIVORE) {
//                    if (executed.contains(entity)) {
//                        continue;
//                    }
//                    ((Creature) entity).makeMove(this.gameMap);
//                    executed.add(entity);
//                }
//            }
//        }
//    }

    /**
     * Executes moves for all creatures (predators and herbivores) on the game map.
     * Ensures that each creature moves only once per turn.
     */

    @Override
    public void execute() {
        HashSet<Entity> executed = new HashSet<>(); // Prevents repeated executions
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        Set<Map.Entry<Coordinates, Entity>> entries = new HashSet<>(entities.entrySet());

        for (Map.Entry<Coordinates, Entity> entry : entries) {
            Coordinates coordinates = entry.getKey();
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
