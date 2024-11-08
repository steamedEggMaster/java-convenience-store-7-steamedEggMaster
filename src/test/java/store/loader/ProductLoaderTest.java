package store.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.io.FileLineReader;
import store.parser.ProductParser;

public class ProductLoaderTest {
    private File tempFile;
    private ProductLoader productLoader;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("products", ".md");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("name,price,quantity,promotion");
            writer.newLine();
            writer.write("콜라,1000,10,탄산2+1");
            writer.newLine();
            writer.write("사이다,1000,8,null");
        }

        FileLineReader fileLineReader = new FileLineReader();
        ProductParser productParser = new ProductParser();
        productLoader = new ProductLoader(fileLineReader, productParser);
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

        List<Product> products = productLoader.loadProducts(filePath);

        assertEquals(2, products.size());
        assertEquals(product1.toString(), products.get(0).toString());
        assertEquals(product2.toString(), products.get(1).toString());
    }
}
