package store.model.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.loader.ItemLoader;
import store.util.message.ErrorMessage;

public class ItemLoaderTest {
    private ItemLoader itemLoader;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();

        Product product1 = new Product("콜라", 1000, 4, "null");
        Product product2 = new Product("콜라", 1000, 6, "탄산2+1");
        Product product3 = new Product("사이다", 1000, 8, "null");
        Product product4 = new Product("오렌지주스", 2000, 5, "MD추천상품");

        products.addAll(List.of(product1, product2, product3, product4));

        itemLoader = new ItemLoader();
    }

    @Test
    void 정상적인_형식_및_값을_가진_Item들이_입력된_경우_예외_발생_안함() {
        String input = "[콜라-3],[사이다-5]";

        Item item1 = new Item("콜라", 3);
        Item item2 = new Item("사이다", 5);

        List<Item> items = itemLoader.loadItems(input, products);

        assertEquals(item1.getQuantity(), items.get(0).getQuantity());
        assertEquals(item1.getName(), items.get(0).getName());
        assertEquals(item2.getQuantity(), items.get(1).getQuantity());
        assertEquals(item2.getName(), items.get(1).getName());
    }

    @Test
    void 대괄호_형식을_지키지_않은_Item이_입력된_경우_예외_발생() {
        String input = "콜라-3],[사이다-5]";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemLoader.loadItems(input, products)
        );
        assertEquals(ErrorMessage.INVALID_FORM_ERROR, exception.getMessage());
    }

    @Test
    void 하이픈_형식을_지키지_않은_Item이_입력된_경우_예외_발생() {
        String input = "[콜라----3],[사이다-5]";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemLoader.loadItems(input, products)
        );
        assertEquals(ErrorMessage.INVALID_FORM_ERROR, exception.getMessage());
    }

    @Test
    void 존재하지_않는_상품이_입력된_경우_예외_발생() {
        String input = "[apple-3],[사이다-5]";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemLoader.loadItems(input, products)
        );
        assertEquals(ErrorMessage.INVALID_PRODUCT_ERROR, exception.getMessage());
    }

    @Test
    void 재고_수량을_초과한_수량이_입력된_경우_예외_발생() {
        String input = "[콜라-15],[사이다-5]";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemLoader.loadItems(input, products)
        );
        assertEquals(ErrorMessage.QUANTITY_EXCEEDED_ERROR, exception.getMessage());
    }

    @Test
    void 잘못된_입력값을_가진_수량이_입력된_경우_예외_발생() {
        String input = "[콜라-4],[사이다-abc]";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemLoader.loadItems(input, products)
        );
        assertEquals(ErrorMessage.INVALID_INPUT_ERROR, exception.getMessage());
    }
}
