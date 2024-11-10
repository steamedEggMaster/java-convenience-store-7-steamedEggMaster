package store.model.service.promotion;

import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class PromotionCalculator {
    private final PromotionDecisionHandler promotionDecisionHandler;
    private int buy;
    private int get;
    private int count;

    public PromotionCalculator() {
        this.promotionDecisionHandler = new PromotionDecisionHandler();
    }

    public void calculatePromotion(Product product, Promotion promotion, Item item) {
        setPromotionFields(promotion);
        count = calculatePromotionCount(product, item);
        updateProduct(product);
        updateItem(product, item);
        promotionDecisionHandler.applyBasedOnRemainingQuantity(product, promotion, item);
    }

    private void setPromotionFields(Promotion promotion) {
        buy = promotion.getBuy();
        get = promotion.getGet();
    }

    private int calculatePromotionCount(Product product, Item item) {
        if (item.getQuantity() <= product.getInventory()) {
            return item.getQuantity() / (buy + get);
        }
        return product.getInventory() / (buy + get);
    }

    private void updateProduct(Product product) {
        product.decrementInventory(count * (buy + get));
    }

    private void updateItem(Product product, Item item) {
        item.incrementPrice(count * buy * product.getPrice());
        item.incrementBonus(count * get);
        item.decreaseRemainingQuantity(count * (buy + get));
    }
}
