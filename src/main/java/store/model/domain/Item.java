package store.model.domain;

public class Item {
    private final String name;
    private int quantity;
    private int remainingQuantity;
    private int price;
    private int bonus;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.remainingQuantity = quantity;
        this.price = 0;
        this.bonus = 0;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public int getPrice() {
        return price;
    }

    public int getBonus() {
        return bonus;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void decreaseRemainingQuantity(int quantity) {
        this.remainingQuantity -= quantity;
    }

    public void incrementPrice(int price) {
        this.price += price;
    }

    public void incrementBonus(int bonus) {
        this.bonus += bonus;
    }
}
