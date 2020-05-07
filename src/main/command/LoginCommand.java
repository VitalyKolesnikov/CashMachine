package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {

            ConsoleHelper.writeMessage(res.getString("specify.data"));

            String str = ConsoleHelper.readString();

            if (str == null) {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            String[] arr = str.split(" ");

            if (arr.length < 2) {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            String cardNumber = arr[0];
            String pin = arr[1];

            if (!cardNumber.matches("\\d{12}") || !pin.matches("\\d{4}")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            if (!validCreditCards.containsKey(cardNumber) || !pin.equals(validCreditCards.getString(cardNumber))) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                continue;
            }

            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
            break;
        }
    }
}