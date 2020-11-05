package com.StockExchangeModel.StockExchange.Interpreter;

public class Action {
    public ActionHandler.ActionType actionType;
    public String[] arguments;

    public Action(ActionHandler.ActionType actionType, String[] arguments) {
        this.actionType = actionType;
        this.arguments = arguments;
    }

    public Action(String actionTypeString, String[] arguments) {
        this(ActionHandler.getActionType(actionTypeString), arguments);
    }

}
