package org.example;

import org.example.utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final GameMap map;
    private final Renderer renderer;
    private final int movesCounter;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private Boolean started;

    public Simulation(GameMap map) {
        this.map = map;
        this.renderer = new Renderer(map);
        this.movesCounter = 0;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
    }

    public void addInitAction(Action action) {
        initActions.add(action);
    }

    public void addTurnAction(Action action) {
        turnActions.add(action);
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.execute();
        }
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.execute();
        }

        int i = 0;
        while (i != 1000) {
            Renderer.clearConsole();
            System.out.println();
            System.out.println();
            System.out.println();
            renderer.render();
            for (Action action : turnActions) {
                action.execute();
            }
            i++;
        }
    }

    public void pauseSimulation() {
    }
}
