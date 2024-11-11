package store.model.service.regular;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class RegularCalculator {

    public void calculate(Product product, Item item) {
        product.decrementInventory(item.getRemainingQuantity());
        item.increaseTotalPrice(item.getRemainingQuantity() * product.getPrice());
        item.increaseRegularPrice(item.getRemainingQuantity() * product.getPrice());
        item.decreaseRemainingQuantity(item.getRemainingQuantity());
    }
}
