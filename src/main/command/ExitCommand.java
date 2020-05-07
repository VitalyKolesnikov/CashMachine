package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (answer != null && answer.equals("y")) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}