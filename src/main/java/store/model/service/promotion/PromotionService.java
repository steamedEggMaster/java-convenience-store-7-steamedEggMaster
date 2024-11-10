package store.model.service.promotion;

import java.util.List;
import store.model.service.ProductFilter;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class PromotionService {
    private final ProductFilter productFilter;
    private final PromotionApplier promotionApplier;

    public PromotionService() {
        productFilter = new ProductFilter();
        promotionApplier = new PromotionApplier();
    }

    public void applyPromotions(List<Product> products, List<Promotion> promotions, List<Item> items) {
        for (Item item : items) {
            List<Product> matchingProducts = productFilter.filterByItemName(products, item);
            applyPromotional(matchingProducts, promotions, item);
        }
    }

    private void applyPromotional(List<Product> products, List<Promotion> promotions, Item item) {
        List<Product> promotionalProducts = productFilter.filterPromotional(products);
        for (Product promotionalProduct : promotionalProducts) {
            promotionApplier.applyPromotion(promotionalProduct, promotions, item);
        }
    }
}
