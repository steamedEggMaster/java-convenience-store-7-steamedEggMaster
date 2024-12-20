package store.model.service.decision;

import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class DecisionActionService {
    public void applyNoDiscountPurchase(Product product, Item item) {
        if (item.getRemainingQuantity() >= product.getInventory()) {
            applyPartialPurchase(product, item);
            return;
        }
        applyFullPurchase(product, item);
    }

    public void applyBonus(Product product, Promotion promotion, Item item) {
        product.decrementInventory(promotion.getGet());
        item.increaseBonus(promotion.getGet(), product.getPrice());
        item.increaseTotalPrice(product.getPrice());
        item.increaseQuantity(promotion.getGet());
    }

    public void decreaseQuantity(Item item) {
        item.decreaseQuantity(item.getRemainingQuantity());
        item.decreaseRemainingQuantity(item.getRemainingQuantity());
    }

    private void applyPartialPurchase(Product product, Item item) {
        int availableInventory = product.getInventory();
        item.increaseTotalPrice(product.getPrice() * availableInventory);
        item.decreaseRemainingQuantity(availableInventory);
        product.decrementInventory(availableInventory);
    }

    private void applyFullPurchase(Product product, Item item) {
        int remainingQuantity = item.getRemainingQuantity();
        product.decrementInventory(remainingQuantity);
        item.increaseTotalPrice(product.getPrice() * remainingQuantity);
        item.decreaseRemainingQuantity(remainingQuantity);
    }
}
