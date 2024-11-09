package store.model.loader;

import java.util.ArrayList;
import java.util.List;
import store.model.domain.Promotion;
import store.model.io.FileLineReader;
import store.model.parser.PromotionParser;

public class PromotionLoader {
    private final FileLineReader fileLineReader;
    private final PromotionParser promotionParser;

    public PromotionLoader(FileLineReader fileLineReader, PromotionParser promotionParser) {
        this.fileLineReader = fileLineReader;
        this.promotionParser = promotionParser;
    }

    public List<Promotion> loadPromotions(String filePath) {
        List<Promotion> promotions = new ArrayList<>();
        List<String> lines = fileLineReader.readLines(filePath);

        lines.forEach(line -> promotions.add(parsePromotion(line)));
        return promotions;
    }

    private Promotion parsePromotion(String line) {
        return promotionParser.parse(line);
    }
}
