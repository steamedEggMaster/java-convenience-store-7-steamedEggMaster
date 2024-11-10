package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PRODUCT_SELECTION_PROMPT = "구매하실 상품명과 수량을 입력해 주세요.";
    private static final String PRODUCT_SELECTION_EXAMPLE = " (예: [사이다-2],[감자칩-1])";
    private static final String BONUS_ADDITION_PROMPT = "추가하시겠습니까? (Y/N)";
    private static final String PURCHASE_DECISION_PROMPT = "그래도 구매하시겠습니까? (Y/N)";

    public String readItem() {
        System.out.print(PRODUCT_SELECTION_PROMPT);
        System.out.println(PRODUCT_SELECTION_EXAMPLE);

        return Console.readLine();
    }

    public String confirmBonus(String name, int bonus) {
        System.out.println();
        System.out.print("현재 " + name + "은(는) " + bonus + "개를 무료로 더 받을 수 있습니다. ");
        System.out.println(BONUS_ADDITION_PROMPT);

        return Console.readLine();
    }

    public String confirmNoDiscountPurchase(String name, int quantity) {
        System.out.println();
        System.out.print("현재 " + name + " " + quantity + "개는 프로모션 할인이 적용되지 않습니다. ");
        System.out.println(PURCHASE_DECISION_PROMPT);

        return Console.readLine();
    }
}
