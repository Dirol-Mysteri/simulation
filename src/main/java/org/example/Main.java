package org.example;

import org.example.actions.MakeMoves;
import org.example.actions.SetDefaultPositions;
import org.example.entities.Entity;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10);
        Simulation simulation = new Simulation(gameMap);

        // Adding actions to the simulation

        simulation.addInitAction(new SetDefaultPositions(gameMap));
//        simulation.addInitAction();
        simulation.addTurnAction(new MakeMoves(gameMap));
//        simulation.addTurnAction();
        simulation.startSimulation();
    }
    // НУЖНО ПРОДОЛЖИТЬ ИСКАТЬ СПОСОБ ОЧИСТИТЬ КОНСОЛЬ
    // ПОПРОБУЙ ЭТОТ МЕТОД https://www.youtube.com/watch?v=0j-0vtRGtT0
}