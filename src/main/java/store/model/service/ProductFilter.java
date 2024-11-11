package store.model.service;

import java.util.List;
import java.util.stream.Collectors;
import store.model.domain.Item;
import store.model.domain.Product;

public class ProductFilter {

    public List<Product> filterByItemName(List<Product> products, Item item) {
        return products.stream()
            .filter(product -> product.getName().equals(item.getName()))
            .collect(Collectors.toList());
    }

    public List<Product> filterPromotional(List<Product> products) {
        return products.stream()
            .filter(product -> !product.getPromotion().equals("null"))
            .toList();
    }

    public List<Product> filterRegular(List<Product> products) {
        return products.stream()
            .filter(product -> product.getPromotion().equals("null"))
            .toList();
    }
}
