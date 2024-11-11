package store.model.service.membership;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.model.domain.Item;

class MembershipCalculatorTest {

    private MembershipCalculator membershipCalculator;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        membershipCalculator = new MembershipCalculator();
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void 멤버십_할인_적용_테스트() {
        setInput("Y\n");

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 8));
        items.add(new Item("사이다", 5));

        items.get(0).increaseRegularPrice(1000 * 8);
        items.get(1).increaseRegularPrice(1200 * 5);

        int discount = membershipCalculator.calculateMembershipDiscount(items);
        int expectedDiscount = (int) ((14000) * 0.3);

        assertEquals(expectedDiscount, discount);
    }

    @Test
    void 멤버십_할인_미적용_테스트() {
        setInput("N\n");

        List<Item> items = new ArrayList<>();
        items.add(new Item("콜라", 8));
        items.add(new Item("사이다", 5));

        items.get(0).increaseRegularPrice(1000 * 8);
        items.get(1).increaseRegularPrice(1200 * 5);

        int discount = membershipCalculator.calculateMembershipDiscount(items);
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes())); // 새로운 입력값 설정
    }
}
