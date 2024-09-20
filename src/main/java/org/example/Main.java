package org.example;

import org.example.actions.SetDefaultPositions;

public class Main {
    public static void main(String[] args) {
        GameMap map = new GameMap(10, 10);
        Simulation simulation = new Simulation(map);

        // Adding actions to the simulation

        simulation.addInitAction(new SetDefaultPositions(map));
//        simulation.addInitAction();
//        simulation.addTurnAction();
//        simulation.addTurnAction();


        simulation.startSimulation();
    }
}