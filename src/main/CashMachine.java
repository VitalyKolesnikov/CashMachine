package main;

import main.command.CommandExecutor;
import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) throws NotEnoughMoneyException {
        Locale.setDefault(Locale.ENGLISH);

        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Good bye!");
        }
    }
}