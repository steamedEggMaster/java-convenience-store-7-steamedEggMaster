package store.domain;

import java.text.DecimalFormat;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "- " + name +
               " " + formatCurrency() + "원" +
               " " + quantity + "개" +
               " " + displayPromotion();
    }

    private String formatCurrency() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

    private String displayPromotion() {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }
}
