package store.model.loader;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Product;
import store.model.io.FileLineReader;
import store.model.parser.ProductParser;

public class ProductLoader {
    private final FileLineReader fileLineReader;
    private final ProductParser productParser;

    public ProductLoader() {
        this.fileLineReader = new FileLineReader();
        this.productParser = new ProductParser();
    }

    public List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        List<String> lines = fileLineReader.readLines(filePath);

        lines.forEach(line -> products.add(parseProduct(line)));
        return products;
    }


    private Product parseProduct(String line) {
        return productParser.parse(line);
    }
}