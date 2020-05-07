package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + "/resources/deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] str;

        while (true) {
                str = ConsoleHelper.getValidTwoDigits(code);
                break;
        }

        int amount = Integer.parseInt(str[0]);
        int number = Integer.parseInt(str[1]);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).addAmount(amount, number);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount * number, code));
    }
}