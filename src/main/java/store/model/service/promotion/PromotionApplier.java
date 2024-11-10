package store.model.service.promotion;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class PromotionApplier {
    private final PromotionCalculator promotionCalculator;

    public PromotionApplier() {
        promotionCalculator = new PromotionCalculator();
    }

    public void applyPromotion(Product product, List<Promotion> promotions, Item item) {
        Promotion promotion = findApplicablePromotion(product, promotions);
        promotionCalculator.calculatePromotion(product, promotion, item);
    }

    private Promotion findApplicablePromotion(Product product, List<Promotion> promotions) {
        return promotions.stream()
            .filter(promotion -> product.getPromotion().equals(promotion.getName()))
            .toList().get(0);
    }
}
