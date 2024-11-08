package store.parser;

import java.time.LocalDate;
import store.domain.Promotion;

public class PromotionParser {
    public Promotion parse(String line) {
        String[] values = line.split(",");
        return buildPromotion(values);
    }

    private Promotion buildPromotion(String[] values) {
        String name = values[0].trim();
        int buy = Integer.parseInt(values[1].trim());
        int get = Integer.parseInt(values[2].trim());
        LocalDate startDate = LocalDate.parse(values[3].trim());
        LocalDate endDate = LocalDate.parse(values[4].trim());

        return new Promotion(name, buy, get, startDate, endDate);
    }
}
