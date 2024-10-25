package org.example.actions;

import org.example.Action;
import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.EntityType;

import java.util.HashMap;

public class MakeMoves implements Action {
    private final GameMap gameMap;

    public MakeMoves(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void execute() {
        HashMap<Coordinates, Entity> entities = gameMap.getEntities();
        for (Entity entity: entities.values()) {
            if (entity.getEntityType() == EntityType.PREDATOR || entity.getEntityType() == EntityType.HERBIVORE) {
                ((Creature)entity).makeMove(this.gameMap);
            }
        }
    }
}

// ДУМАЮ НАДО ДОБАВИТЬ КАЖДЫЙ ВИД СУЩЕСТВ В ОТДЕЛЬНЫЕ HASHMAPS с той целью, чтобы по несколько раз не проходить по массиву.
