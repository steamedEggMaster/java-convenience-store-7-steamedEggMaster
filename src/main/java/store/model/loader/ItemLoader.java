package store.model.loader;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.parser.ItemParser;
import store.util.validator.item.ItemInfoValidator;

public class ItemLoader {
    private final ItemParser itemParser;
    private final ItemInfoValidator itemInfoValidator;

    public ItemLoader() {
        itemParser = new ItemParser();
        itemInfoValidator = new ItemInfoValidator();
    }

    public List<Item> loadItems(String input, List<Product> products) {
        List<Item> items = new ArrayList<>();

        for (String strItem : input.split(",")) {
            Item item = itemParser.parse(strItem);
            validateItem(products, item);
            items.add(item);
        }
        return items;
    }

    private void validateItem(List<Product> products, Item item) {
        itemInfoValidator.validateItemExists(products, item);
        itemInfoValidator.validateAvailableQuantity(products, item);
    }
}
