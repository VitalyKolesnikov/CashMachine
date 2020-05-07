package main;

import main.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + "/resources/common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if (str.equalsIgnoreCase("exit")) {
                ConsoleHelper.writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            } else {
                return str;
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        if (code == null || code.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String str = readString();
        if (str == null || !str.matches("\\d+\\s\\d+")) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }

        return str.split(" ");
    }

    public static Operation askOperation() throws InterruptOperationException {
        for (Operation opr : Operation.values()) {
            if (opr.ordinal() != 0) {
                ConsoleHelper.writeMessage(opr.ordinal() + " - " + opr);
            }
        }

        try {
            String str = readString();
            if (str == null) return askOperation();
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(str));
        } catch (IllegalArgumentException e) {
            return askOperation();
        }
    }
}