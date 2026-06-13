package ru.mtuci.petunin;

import java.util.List;

public class CartCalculator {
    public double total(List<Double> prices) {
        validate(prices);
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double discountedTotal(List<Double> prices, double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100");
        }
        return total(prices) * (100 - percent) / 100;
    }

    public double average(List<Double> prices) {
        return total(prices) / prices.size();
    }

    private void validate(List<Double> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Список цен не должен быть пустым");
        }
    }
}
