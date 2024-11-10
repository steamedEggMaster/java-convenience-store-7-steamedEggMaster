package store.model.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.io.FileLineReader;
import store.model.loader.ProductLoader;
import store.model.parser.ProductParser;

public class ProductLoaderTest {
    private File tempFile;
    private ProductLoader productLoader;
    private List<Promotion> promotions;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("products", ".md");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("name,price,quantity,promotion");
            writer.newLine();
            writer.write("콜라,1000,10,탄산2+1");
            writer.newLine();
            writer.write("사이다,1000,8,null");
            writer.newLine();
            writer.write("오렌지주스,2000,0,MD추천상품");
        }

        productLoader = new ProductLoader();

        promotions = List.of(
            new Promotion("탄산2+1", 2, 1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)),
            new Promotion("MD추천상품", 1, 1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31))
        );
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void 파일에서_읽은_라인들을_제대로_Product_객체로_만드는지_테스트() {
        String filePath = tempFile.getAbsolutePath();

        Product product1 = new Product("콜라", 1000, 10, "탄산2+1");
        Product product2 = new Product("사이다", 1000, 8, "null");
        Product product3 = new Product("오렌지주스", 2000, 0, "MD추천상품");

        List<Product> products = productLoader.loadProducts(filePath, promotions);

        assertEquals(3, products.size());
        assertEquals(product1.toString(), products.get(0).toString());
        assertEquals(product2.toString(), products.get(1).toString());
        assertEquals(product3.toString(), products.get(2).toString());
    }
}
