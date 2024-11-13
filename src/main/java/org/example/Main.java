package org.example;

import org.example.actions.MakeMoves;
import org.example.actions.SetDefaultPositions;

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
    // Я думаю надо реализовать логику запоминания направления последнего пути, и как-то эту логику отыграть, чтобы не возвращаться в предыдущие клетки.
}