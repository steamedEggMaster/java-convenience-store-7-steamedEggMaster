package store.parser;

import store.domain.Product;

public class ProductParser {
    public Product parse(String line) {
        String[] values = line.split(",");

        String name = values[0].trim();
        int price = Integer.parseInt(values[1].trim());
        int quantity = Integer.parseInt(values[2].trim());
        String promotion = values[3].trim();

        return new Product(name, price, quantity, promotion);
    }
}