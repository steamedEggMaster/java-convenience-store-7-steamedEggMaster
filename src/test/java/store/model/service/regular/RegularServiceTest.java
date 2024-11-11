package store.model.service.regular;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;

public class RegularServiceTest {
    private RegularService regularService;

    @BeforeEach
    void setUp() {
        regularService = new RegularService();
    }

    @Test
    void 일반_재고_계산_테스트() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("콜라", 1000, 10, "null"));
        products.add(new Product("사이다", 1200, 8, "null"));

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 5));
        items.add(new Item("사이다", 3));

        regularService.calculateRegularPrice(products, items);

        assertEquals(5000, items.get(0).getRegularPrice());
        assertEquals(3600, items.get(1).getRegularPrice());
    }
}
