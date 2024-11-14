package org.example;

import org.example.actions.Action;
import org.example.actions.ActionFactory;
import org.example.enums.InitActionType;
import org.example.enums.TurnActionType;
import org.example.utils.CLI;
import org.example.utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private GameMap gameMap;
    private final Renderer renderer;
    private final CLI CLI;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private int movesCounter;
    private Boolean isRunning;

    public Simulation(int height, int width) {
        this.gameMap = new GameMap(height, width);
        this.renderer = new Renderer(gameMap);
        this.CLI = new CLI(gameMap);
        this.movesCounter = 0;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
    }

    public void addInitAction(InitActionType initActionType) {
        initActions.add(ActionFactory.create(gameMap, initActionType));
    }

    public void addTurnAction(TurnActionType action) {
        turnActions.add(ActionFactory.create(gameMap, action));

    }

    public void nextTurn() {
        renderer.render();
            movesCounter++;
            for (Action action : turnActions) {
                action.execute();
            }
            renderer.render();
            if (gameMap.getHerbivoresQuantity() < 1) {
                isRunning = false;
                System.out.println(movesCounter);
            }
    }

    public void startSimulation() {
        CLI.mainMenu();
        for (Action action : initActions) {
            action.execute();
        }
        isRunning = true;
        renderer.render();
        while (isRunning) {
            movesCounter++;
            for (Action action : turnActions) {
                action.execute();
            }
            renderer.render();
            if (gameMap.getHerbivoresQuantity() < 1) {
                isRunning = false;
                System.out.println(movesCounter);
            }
        }
    }

    public void pauseSimulation() {
        isRunning = false;
    }

}
