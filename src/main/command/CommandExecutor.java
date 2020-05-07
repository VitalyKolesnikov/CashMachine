package main.command;

import main.Operation;
import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    private CommandExecutor() {
    }

    static {
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException {
        allKnownCommandsMap.get(operation).execute();
    }
}