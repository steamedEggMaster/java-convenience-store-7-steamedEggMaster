package store.model.service.promotion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
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
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        promotionService = new PromotionService();
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("구매 상품 중 프로모션이 있는 경우 프로모션 적용 및 남은 개수 처리 1. 프로모션_무료_추가에_Y")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리1() {
        setInput("Y\n");

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
        assertEquals(2, items.get(0).getBonus()); // bonus 추가됨
        assertEquals(4000, items.get(0).getPrice());
        assertEquals(0, items.get(0).getRemainingQuantity());

        assertEquals(3, items.get(1).getQuantity());
        assertEquals(0, items.get(1).getBonus());
        assertEquals(0, items.get(1).getPrice());
        // 프로모션이 있는 Item만 계산하기 때문에 일반 상품은 계산되지 않음
    }

    @Test
    @DisplayName("구매 상품 중 프로모션이 있는 경우 프로모션 적용 및 남은 개수 처리 2. 프로모션_무료_추가에_N")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리2() {
        setInput("N\n");

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
        assertEquals(1, items.get(0).getBonus()); // bonus 추가 안됨
        assertEquals(4000, items.get(0).getPrice());
        assertEquals(0, items.get(0).getRemainingQuantity());
    }

    @Test
    @DisplayName("구매 상품 중 프로모션이 있는 경우 프로모션 적용 및 남은 개수 처리 3. 할인 미적용에_Y")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리3() {
        setInput("Y\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 4));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(4, items.get(0).getQuantity());
        assertEquals(1, items.get(0).getBonus());
        assertEquals(3000, items.get(0).getPrice()); // 총 3개가 계산됨
        assertEquals(0, items.get(0).getRemainingQuantity());
    }

    @Test
    @DisplayName("구매 상품 중 프로모션이 있는 경우 프로모션 적용 및 남은 개수 처리 4. 할인 미적용에_N")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리4() {
        setInput("N\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 4));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(3, items.get(0).getQuantity());
        assertEquals(1, items.get(0).getBonus());
        assertEquals(2000, items.get(0).getPrice()); // 총 2개가 계산됨
        assertEquals(0, items.get(0).getRemainingQuantity());
    }

    @Test
    @DisplayName("5. 프로모션 재고보다 크고 할인 미적용에 Y")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리5() {
        setInput("Y\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 12));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(12, items.get(0).getQuantity());
        assertEquals(3, items.get(0).getBonus());
        assertEquals(7000, items.get(0).getPrice()); // 프로모션 및 질문 적용에 대한 값
        assertEquals(2, items.get(0).getRemainingQuantity()); // 일반 재고에 적용될 값
        assertEquals(0, products.get(0).getInventory()); // 프로모션 재고 전부 소진
    }

    @Test
    @DisplayName("6. 프로모션 재고보다 크고 할인 미적용에 N")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리6() {
        setInput("N\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 12));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(9, items.get(0).getQuantity());
        assertEquals(3, items.get(0).getBonus());
        assertEquals(6000, items.get(0).getPrice());
        assertEquals(0, items.get(0).getRemainingQuantity());
        assertEquals(1, products.get(0).getInventory());
    }

    @Test
    @DisplayName("7. 프로모션 재고와 같고 할인 미적용에 Y")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리7() {
        setInput("Y\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 10));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(10, items.get(0).getQuantity());
        assertEquals(3, items.get(0).getBonus());
        assertEquals(7000, items.get(0).getPrice());
        assertEquals(0, items.get(0).getRemainingQuantity());
        assertEquals(0, products.get(0).getInventory());
    }

    @Test
    @DisplayName("8. 프로모션 재고와 같고 할인 미적용에 N")
    void 구매_상품_중_프로모션이_있는_경우_프로모션_적용_및_남은_개수_처리8() {
        setInput("N\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "탄산2+1"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("탄산2+1", 2, 1,
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 12));
        items.add(new Item("사이다", 3));

        promotionService.applyPromotions(products, promotions, items);

        assertEquals(9, items.get(0).getQuantity());
        assertEquals(3, items.get(0).getBonus());
        assertEquals(6000, items.get(0).getPrice());
        assertEquals(0, items.get(0).getRemainingQuantity());
        assertEquals(1, products.get(0).getInventory());
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes())); // 새로운 입력값 설정
    }
}
