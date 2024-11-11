package store.view;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.service.ReceiptPrinter;

public class OutputView {
    private static final String GREETING_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String PRODUCTS_DESCRIPTION = "현재 보유하고 있는 상품입니다.";
    private final ReceiptPrinter receiptPrinter;

    public OutputView() {
        this.receiptPrinter = new ReceiptPrinter();
    }

    public void printProducts(List<Product> products) {
        System.out.println(GREETING_MESSAGE);
        System.out.println(PRODUCTS_DESCRIPTION);

        products.forEach(System.out::println);
        System.out.println();
    }

    public void printReceipt(List<Item> items) {
        receiptPrinter.print(items);
    }
}
