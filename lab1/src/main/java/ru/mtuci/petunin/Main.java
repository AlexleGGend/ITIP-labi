package ru.mtuci.petunin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CartCalculator calculator = new CartCalculator();
        List<Double> prices = List.of(1200.0, 740.0, 310.0, 2200.0);
        double total = calculator.total(prices);
        double discount = calculator.discountedTotal(prices, 10);

        System.out.println("Товары: " + prices);
        System.out.println("Сумма: " + total);
        System.out.println("После скидки: " + discount);
        System.out.println("Средняя цена: " + calculator.average(prices));
    }
}
