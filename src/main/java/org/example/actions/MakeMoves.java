package org.example.actions;

import org.example.Action;
import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.enums.EntityType;
import org.example.utils.Renderer;

import java.util.HashSet;

public class MakeMoves implements Action {
    private final GameMap gameMap;
    private final Renderer renderer;

    public MakeMoves(GameMap gameMap) {
        this.gameMap = gameMap;
        this.renderer = new Renderer(gameMap);
    }

    @Override
    public void execute() {
        HashSet<Entity> executed = new HashSet<>();
        for (int i = 0; i < this.gameMap.getN(); i++) {
            for (int j = 0; j < this.gameMap.getM(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Entity entity = this.gameMap.getEntities().get(coordinates);
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
