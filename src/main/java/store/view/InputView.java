package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PRODUCT_SELECTION_PROMPT = "구매하실 상품명과 수량을 입력해 주세요.";
    private static final String PRODUCT_SELECTION_EXAMPLE = " (예: [사이다-2],[감자칩-1])";

    public String readItem() {
        System.out.print(PRODUCT_SELECTION_PROMPT);
        System.out.println(PRODUCT_SELECTION_EXAMPLE);

        return Console.readLine();
    }
}
