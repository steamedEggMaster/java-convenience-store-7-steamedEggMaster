package store.loader;

import java.util.ArrayList;
import java.util.List;
import store.domain.Product;
import store.io.FileLineReader;
import store.parser.ProductParser;

public class ProductLoader {
    private final FileLineReader fileLineReader;
    private final ProductParser productParser;

    public ProductLoader(FileLineReader fileLineReader, ProductParser productParser) {
        this.fileLineReader = fileLineReader;
        this.productParser = productParser;
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