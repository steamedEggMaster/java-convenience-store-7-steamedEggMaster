package store.model.service.regular;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.service.ProductFilter;

public class RegularService {
    private final ProductFilter productFilter;
    private final RegularCalculator regularCalculator;

    public RegularService() {
        this.productFilter = new ProductFilter();
        this.regularCalculator = new RegularCalculator();
    }

    public void calculateRegularPrice(
        List<Product> products, List<Item> items) {
        for (Item item : items) {
            List<Product> matchingProducts = productFilter.filterByItemName(products, item);
            calculateRegular(matchingProducts, item);
        }
    }

    private void calculateRegular(List<Product> products, Item item) {
        List<Product> regularProducts = productFilter.filterRegular(products);
        for (Product regularProduct : regularProducts) {
            regularCalculator.calculate(regularProduct, item);
        }
    }
}
