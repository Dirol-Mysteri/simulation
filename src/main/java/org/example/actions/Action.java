package org.example.actions;

import org.example.enums.ActionType;

public abstract class Action {
    private final ActionType actionType;

    protected Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public abstract void execute();
}
