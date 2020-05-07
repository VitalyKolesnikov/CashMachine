package main;

import main.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder());

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (!denominations.containsKey(denomination)) {
            denominations.put(denomination, count);
        } else {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
    }

    public int getTotalAmount() {
        return denominations.entrySet()
                .stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();
    }

    public boolean hasMoney() {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        int currentAmount = 0;

        outer:
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int currentBanknote = entry.getKey();
            int banknotesLeft = entry.getValue();
            while (banknotesLeft > 0) {
                if (currentAmount + currentBanknote <= expectedAmount) {
                    currentAmount += entry.getKey();
                    if (!result.containsKey(currentBanknote)) {
                        result.put(currentBanknote, 1);
                    } else {
                        result.put(currentBanknote, result.get(currentBanknote) + 1);
                    }
                    if (currentAmount == expectedAmount) {
                        break outer;
                    }
                    banknotesLeft--;
                } else {
                    continue outer;
                }
            }
        }
        if (currentAmount == expectedAmount) {
            result.forEach((k, v) -> denominations.put(k, denominations.get(k) - v));
            return result;
        } else throw new NotEnoughMoneyException();
    }
}