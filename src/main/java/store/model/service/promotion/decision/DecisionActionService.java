package store.model.service.promotion.decision;

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
        item.incrementBonus(promotion.getGet());
    }

    public void decreaseQuantity(Item item) {
        item.decreaseQuantity(item.getRemainingQuantity());
        item.decreaseRemainingQuantity(item.getRemainingQuantity());
    }

    private void applyPartialPurchase(Product product, Item item) {
        int availableInventory = product.getInventory();
        item.incrementPrice(product.getPrice() * availableInventory);
        item.decreaseRemainingQuantity(availableInventory);
        product.decrementInventory(availableInventory);
    }

    private void applyFullPurchase(Product product, Item item) {
        int remainingQuantity = item.getRemainingQuantity();
        product.decrementInventory(remainingQuantity);
        item.incrementPrice(product.getPrice() * remainingQuantity);
        item.decreaseRemainingQuantity(remainingQuantity);
    }
}
