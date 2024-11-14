package org.example.actions;

import org.example.GameMap;
import org.example.enums.InitActionType;
import org.example.enums.TurnActionType;

public class ActionFactory {
    public static Action create(GameMap map, InitActionType actionType) {
        Action result = null;
        switch (actionType) {
            case InitActionType.SET_DEFAULT_POSITIONS:
                result = new SetDefaultPositions(map);
        }
        return result;
    }

    public static Action create(GameMap map, TurnActionType actionType) {
        Action result = null;
        switch (actionType) {
            case TurnActionType.MAKE_MOVES:
                result = new MakeMoves(map);
        }
        return result;
    }
}
