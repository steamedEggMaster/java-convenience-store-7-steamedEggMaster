package store.model.loader.file;

import java.util.List;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class FileLoader {
    private static final String PROMOTION_FILE = "promotions.md";
    private static final String PRODUCT_FILE = "products.md";
    private final PromotionLoader promotionLoader;
    private final ProductLoader productLoader;

    private List<Promotion> promotions;
    private List<Product> products;

    public FileLoader() {
        this.promotionLoader = new PromotionLoader();
        this.productLoader = new ProductLoader();
    }

    public void loadFile() {
        String promotionFile = getClass().getClassLoader().getResource(PROMOTION_FILE).getPath();
        promotions = promotionLoader.loadPromotions(promotionFile);

        String productFile = getClass().getClassLoader().getResource(PRODUCT_FILE).getPath();
        products = productLoader.loadProducts(productFile, promotions);
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public List<Product> getProducts() {
        return products;
    }
}
