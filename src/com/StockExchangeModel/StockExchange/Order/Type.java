package com.StockExchangeModel.StockExchange.Order;

import java.util.Objects;

public class Type {
    public enum TypeEnum {
        BUY,
        SELL,
        TRANSACTION,
        NULL
    }

    static String getStringFromType(TypeEnum type) {
        switch (type) {
            case BUY:
                return "BUY";
            case SELL:
                return "SELL";
            case TRANSACTION:
                return "TRANSACTION";
            default:
                return "NULL";
        }
    }

    static TypeEnum getTypeFromString(String type) {
        String s = type.toUpperCase().strip();
        if (s.length() > 0) {
            switch (s) {
                case "BUY":
                    return TypeEnum.BUY;
                case "SELL":
                    return TypeEnum.SELL;
                case "TRANSACTION":
                    return TypeEnum.TRANSACTION;
                case "NULL":
                default:
                    return TypeEnum.NULL;
            }
        }
        return TypeEnum.NULL;
    }

    TypeEnum type;

    Type(String type) {
        this.type = getTypeFromString(type);
    }

    Type(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getTypeEnum() {
        return this.type;
    }

    public String getTypeString() {
        return getStringFromType(this.getTypeEnum());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return type == type1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Type{" +
                "type=" + getTypeString() +
                '}';
    }
}
