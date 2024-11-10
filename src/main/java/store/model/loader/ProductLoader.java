package store.model.loader;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.io.FileLineReader;
import store.model.parser.ProductParser;

public class ProductLoader {
    private final FileLineReader fileLineReader;
    private final ProductParser productParser;

    public ProductLoader() {
        this.fileLineReader = new FileLineReader();
        this.productParser = new ProductParser();
    }

    public List<Product> loadProducts(String filePath, List<Promotion> promotions) {
        List<String> promotionNames = getPromotionNames(promotions);

        return fileLineReader.readLines(filePath).stream()
            .map(this::parseProduct)
            .filter(product -> promotionNames.contains(product.getPromotion()) ||
                product.getPromotion().equals("null"))
            .toList(); // 프로모션 진행 중에 있는 상품 or 일반 상품만 필터링
    }

    private Product parseProduct(String line) {
        return productParser.parse(line);
    }

    private List<String> getPromotionNames(List<Promotion> promotions) {
        return promotions.stream()
            .map(Promotion::getName)
            .toList();
    }
}