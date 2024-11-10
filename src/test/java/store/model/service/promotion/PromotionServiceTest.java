package store.model.service.promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionServiceTest {

    private PromotionService promotionService;

    @BeforeEach
    void setUp() {
        promotionService = new PromotionService();
    }

    @Test
    void 프로모션_적용_상품은_프로모션이_적용되는지_일반_상품은_적용이_안되고있는지_테스트() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 5));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(5, items.get(0).getQuantity());
        assertEquals(1, items.get(0).getBonus());
        assertEquals(2000, items.get(0).getPrice());
        // 프로모션에 적용되는 값만 계산, 나머지는 계산 아직 안됨

        assertEquals(3, items.get(1).getQuantity());
        assertEquals(0, items.get(1).getBonus()); // 프로모션이 없으므로 보너스는 0
        assertEquals(0, items.get(1).getPrice());
        // 프로모션이 있는 Item만 계산하기 때문에 일반 상품은 계산되지 않음
    }
}
