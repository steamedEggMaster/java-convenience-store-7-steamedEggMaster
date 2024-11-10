package store.model.domain;

public class Item {
    private final String name;
    private int quantity;
    private int remainingQuantity;
    private int totalPrice;
    private int bonus;
    private int bonusPrice;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.remainingQuantity = quantity;
        this.totalPrice = 0;
        this.bonus = 0;
        this.bonusPrice = 0;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getBonus() {
        return bonus;
    }

    public int getBonusPrice() {
        return bonusPrice;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void decreaseRemainingQuantity(int quantity) {
        this.remainingQuantity -= quantity;
    }

    public void increaseTotalPrice(int price) {
        this.totalPrice += price;
    }

    public void increaseBonus(int bonus, int price) {
        this.bonus += bonus;
        this.bonusPrice += price;
    }
}
