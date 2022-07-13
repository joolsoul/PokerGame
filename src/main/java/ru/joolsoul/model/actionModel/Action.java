package ru.joolsoul.model.actionModel;

public class Action {

    private ActionType actionType;
    private double actionValue;

    public Action(ActionType actionType, double actionValue) {
        this.actionType = actionType;
        if (!actionType.equals(ActionType.CHECK)) {
            this.actionValue = actionValue;
        }
    }

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public double getActionValue() {
        return actionValue;
    }

    public void setActionValue(double actionValue) {
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return "Action type: " + actionType.toString() + "\n" +
                "Action value: " + actionValue + '$';
    }
}
