package store.model.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import store.model.domain.Promotion;
import store.model.io.FileLineReader;
import store.model.parser.PromotionParser;

public class PromotionLoader {
    private final FileLineReader fileLineReader;
    private final PromotionParser promotionParser;

    public PromotionLoader() {
        this.fileLineReader = new FileLineReader();
        this.promotionParser = new PromotionParser();
    }

    public List<Promotion> loadPromotions(String filePath) {
        List<Promotion> promotions = new ArrayList<>();
        List<String> lines = fileLineReader.readLines(filePath);

        lines.forEach(line -> promotions.add(parsePromotion(line)));
        return promotions.stream()
            .filter(Promotion::isActive) // 현재 프로모션 진행중이 아니면 굳이 가지고 있을 필요 없음
            .collect(Collectors.toList());
    }

    private Promotion parsePromotion(String line) {
        return promotionParser.parse(line);
    }
}
