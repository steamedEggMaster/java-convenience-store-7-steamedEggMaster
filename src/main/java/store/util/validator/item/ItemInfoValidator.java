package store.util.validator.item;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.util.message.ErrorMessage;

public class ItemInfoValidator {

    public void validateItemExists(List<Product> products, Item item) {
        long count = products.stream()
            .filter(product -> product.getName().equals(item.getName()))
            .count();

        if (count < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRODUCT_ERROR);
        }
    }

    public void validateSufficientQuantity(List<Product> products, Item item) {
        int availableQuantity = products.stream()
            .filter(product -> product.getName().equals(item.getName()))
            .mapToInt(Product::getQuantity)
            .sum();

        if (availableQuantity < item.getQuantity()) {
            throw new IllegalArgumentException(ErrorMessage.QUANTITY_EXCEEDED_ERROR);
        }
    }
}
