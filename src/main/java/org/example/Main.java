package org.example;

import org.example.enums.InitActionType;
import org.example.enums.TurnActionType;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(20, 20);

        // Adding actions to the simulation
        simulation.addInitAction(InitActionType.SET_DEFAULT_POSITIONS);
        simulation.addTurnAction(TurnActionType.MAKE_MOVES);
        simulation.startSimulation();
    }
}
