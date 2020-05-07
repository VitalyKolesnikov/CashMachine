package main;

public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        if (i < 1 || i > Operation.values().length) {
            throw new IllegalArgumentException();
        }
        return Operation.values()[i];
    }
}