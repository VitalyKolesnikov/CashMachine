package main.command;

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));

        boolean hasMoney = false;

        for (CurrencyManipulator cm : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            String code = cm.getCurrencyCode();
            if (cm.hasMoney()) {
            ConsoleHelper.writeMessage(code + " - " + cm.getTotalAmount());
            hasMoney = true;
            }
        }
        if (!hasMoney) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}