package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import main.exception.InterruptOperationException;
import main.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + "/resources/withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String str = ConsoleHelper.readString();

            if (str == null || !str.matches("\\d+") || str.equals("0")) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            int amount = Integer.parseInt(str);

            if (!cm.isAmountAvailable(amount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            Map<Integer, Integer> withdrawMap = null;
            try {
                withdrawMap = cm.withdrawAmount(amount);
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }

            withdrawMap.forEach((k, v) -> System.out.println("\t" + k + " - " + v));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, cm.getCurrencyCode()));
            break;
        }
    }
}