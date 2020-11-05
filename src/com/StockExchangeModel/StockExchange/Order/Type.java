package com.StockExchangeModel.StockExchange.Order;

import java.util.Objects;

public class Type {
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

    public enum TypeEnum{
        BUY,
        SELL,
        TRANSACTION,
        UNKNOWN
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
                return "UNKNOWN";
        }
    }

    static TypeEnum getTypeFromString(String type) {
        if (type.toUpperCase().compareTo("BUY") == 0) {
            return TypeEnum.BUY;
        }
        else if (type.toUpperCase().compareTo("SELL") == 0) {
            return TypeEnum.SELL;
        }
        else if (type.toUpperCase().compareTo("TRANSACTION") == 0) {
            return TypeEnum.TRANSACTION;
        }
        else
            return TypeEnum.UNKNOWN;
    }

}
