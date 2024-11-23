package org.example.actions;

import org.example.Coordinates;
import org.example.GameMap;
import org.example.entities.*;
import org.example.enums.ActionType;

import java.util.HashMap;
import java.util.Random;

public class SetPositions extends Action {

    private final GameMap gameMap;
    private final Random random = new Random();
    private final HashMap<Coordinates, Entity> entities;

    // Set Default
    public SetPositions(GameMap gameMap) {
        super(ActionType.INIT_ACTION);
        this.gameMap = gameMap;
        this.entities = gameMap.getEntities();
    }


    private void fillEntities(int maxCount, EntityFactory factory) {
        int i = 0;
        while (i < maxCount) {
            Coordinates coordinates = generateRandomCoordinates();
            if (!entities.containsKey(coordinates)) {
                entities.put(coordinates, factory.create(coordinates));
                i++;
            }
        }
    }

    private Coordinates generateRandomCoordinates() {
        return new Coordinates(random.nextInt(gameMap.getN()), random.nextInt(gameMap.getM()));
    }


    @Override
    public void execute() {
        int predators = gameMap.getPredatorsQTY();
        int herbivores = gameMap.getHerbivoresQTY();
        int resources = gameMap.getResourcesQTY();
        int staticObjects = gameMap.getStaticObjectsQTY();

        fillEntities(herbivores, (coordinates) -> new Herbivore(coordinates.getN(), coordinates.getM()));
        fillEntities(predators, (coordinates) -> new Predator(coordinates.getN(), coordinates.getM()));
        fillEntities(resources, (coordinates) -> new Grass(coordinates.getN(), coordinates.getM()));
        fillEntities(staticObjects, (coordinates) -> new StaticObject(coordinates.getN(), coordinates.getM()));
    }
}
