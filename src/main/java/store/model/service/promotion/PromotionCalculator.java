package store.model.service.promotion;

import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class PromotionCalculator {
    private int buy;
    private int get;
    private int count;

    public void calculatePromotion(Product product, Promotion promotion, Item item) {
        setPromotionFields(promotion);
        count = calculatePromotionCount(product, item);
        updateProduct(product);
        updateItem(product, item);
    }

    private void setPromotionFields(Promotion promotion) {
        buy = promotion.getBuy();
        get = promotion.getGet();
    }

    private int calculatePromotionCount(Product product, Item item) {
        if (item.getQuantity() <= product.getQuantity()) {
            return item.getQuantity() / (buy + get);
        }
        return product.getQuantity() / (buy + get);
    }

    private void updateProduct(Product product) {
        product.decrementQuantity(count * (buy + get));
    }

    private void updateItem(Product product, Item item) {
        item.incrementPrice(count * buy * product.getPrice());
        item.incrementBonus(count * get);
        item.decreaseRemainingQuantity(count * (buy + get));
    }
}
