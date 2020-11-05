package com.StockExchangeModel.StockExchange.Interpreter;

import com.StockExchangeModel.StockExchange.StockExchange;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class ActionHandler {
    public enum ActionType {
        SHOW,
        SHOW_ALL,
        SHOW_CATEGORY,
        ADD,
        DELETE,
        STAGE,
        EXECUTE,
        NULL
    }

    public static String getActionString(ActionType action) {
        switch (action) {
            case SHOW:
                return "SHOW";
            case SHOW_ALL:
                return "SHOW_ALL";
            case SHOW_CATEGORY:
                return "SHOW_CATEGORY";
            case ADD:
                return "ADD";
            case DELETE:
                return "DELETE";
            case STAGE:
                return "STAGE";
            case EXECUTE:
                return "EXECUTE";
            case NULL:
            default:
                return "NULL";
        }
    }

    public static ActionType getActionType(String actionString) {
       String s = actionString.toUpperCase().strip();
       if (s.length() > 0) {
           switch (s) {
               case "SHOW":
                   return ActionType.SHOW;
               case "SHOW_ALL":
                   return ActionType.SHOW_ALL;
               case "SHOW_CATEGORY":
                   return ActionType.SHOW_CATEGORY;
               case "ADD":
                   return ActionType.ADD;
               case "DELETE":
                   return ActionType.DELETE;
               case "STAGE":
                   return ActionType.STAGE;
               case "EXECUTE":
                   return ActionType.EXECUTE;
               default:
                   return ActionType.NULL;
           }
       }
       return ActionType.NULL;
    }

    public StockExchange context;
    public String actionLeader;

    public ActionHandler(StockExchange context, String actionLeader) {
        this.context = context;
        this.actionLeader = actionLeader;
    }

    public String[] handleAction(Action action) {
        return null;
    }
}
