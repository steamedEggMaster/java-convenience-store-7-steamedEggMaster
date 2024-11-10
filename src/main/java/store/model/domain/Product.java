package store.model.domain;

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

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    public void decrementQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "- " + name +
               " " + formatCurrency() +
               " " + displayQuantity() +
               " " + displayPromotion();
    }

    private String formatCurrency() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price) + "원";
    }

    private String displayQuantity() {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }

    private String displayPromotion() {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }
}
