package store.model.domain;

import java.text.DecimalFormat;

public class Product {
    private final String name;
    private final int price;
    private int inventory;
    private final String promotion;

    public Product(String name, int price, int inventory, String promotion) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public String getPromotion() {
        return promotion;
    }

    public void decrementQuantity(int quantity) {
        this.inventory -= quantity;
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
        if (inventory == 0) {
            return "재고 없음";
        }
        return inventory + "개";
    }

    private String displayPromotion() {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }
}
