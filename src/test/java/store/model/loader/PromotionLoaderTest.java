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
import store.model.domain.Promotion;
import store.model.loader.file.PromotionLoader;

public class PromotionLoaderTest {
    private File tempFile;
    private PromotionLoader promotionLoader;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("promotions", ".md");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("name,buy,get,start_date,end_date");
            writer.newLine();
            writer.write("탄산2+1,2,1,2024-01-01,2024-12-31");
        }

        promotionLoader = new PromotionLoader();
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void 파일에서_읽은_라인들을_제대로_Promotion_객체로_만드는지_테스트() {
        String filePath = tempFile.getAbsolutePath();

        Promotion promotion1 = new Promotion(
            "탄산2+1", 2, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31"));

        List<Promotion> promotions = promotionLoader.loadPromotions(filePath);

        assertEquals(1, promotions.size());
        assertEquals(promotion1.getName(), promotions.get(0).getName());
        assertEquals(promotion1.getBuy(), promotions.get(0).getBuy());
        assertEquals(promotion1.getGet(), promotions.get(0).getGet());
        assertEquals(promotion1.getStartDate(), promotions.get(0).getStartDate());
        assertEquals(promotion1.getEndDate(), promotions.get(0).getEndDate());
    }
}
