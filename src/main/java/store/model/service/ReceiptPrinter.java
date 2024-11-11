package store.model.service;

import java.text.NumberFormat;
import java.util.List;
import store.model.domain.Item;
import store.model.service.membership.MembershipCalculator;

public class ReceiptPrinter {
    private static final String RECEIPT_HEADER_MESSAGE = "==============W 편의점================";
    private static final String RECEIPT_INFO_MESSAGE = "상품명\t\t수량\t금액";
    private static final String RECEIPT_MIDDLE_MESSAGE = "=============증\t정===============";
    private static final String RECEIPT_FOOTER_MESSAGE = "====================================";
    private static final String TOTAL_PRICE_MESSAGE = "총구매액\t\t";
    private static final String PROMOTION_DISCOUNT_MESSAGE = "행사할인\t\t\t-";
    private static final String MEMBERSHIP_DISCOUNT_MESSAGE = "멤버십할인\t\t\t-";
    private static final String COST_MESSAGE = "내실돈\t\t\t";
    private final MembershipCalculator membershipCalculator;
    private List<Item> items;

    public ReceiptPrinter() {
        this.membershipCalculator = new MembershipCalculator();
    }

    public void print(List<Item> items) {
        this.items = items;
        System.out.println();
        printHeader();
        printMiddle();
        printFooter();
    }

    private void printHeader() {
        System.out.println(RECEIPT_HEADER_MESSAGE);
        System.out.println(RECEIPT_INFO_MESSAGE);
        printPurchasingItems();
    }

    private void printMiddle() {
        System.out.println(RECEIPT_MIDDLE_MESSAGE);
        printPromotionItems();
    }

    private void printFooter() {
        System.out.println(RECEIPT_FOOTER_MESSAGE);
        printTotalPrice();
        printPromotionDiscount();
        printMembershipDiscount();
        printCost();
    }

    private void printPurchasingItems() {
        items.forEach(item -> System.out.println(
                item.getName() + "\t\t" +
                item.getQuantity() + "\t" +
                formatPrice(item.getTotalPrice())));
    }

    private void printPromotionItems() {
        items.stream()
            .filter(item -> item.getBonus() != 0)
            .forEach(item -> System.out.println(item.getName() + "\t\t" + item.getBonus()));
    }

    private void printTotalPrice() {
        int totalQuantity = getTotalQuantity();
        int totalPrice = getTotalPrice();

        System.out.print(TOTAL_PRICE_MESSAGE + totalQuantity + "\t" + formatPrice(totalPrice));
    }

    private void printPromotionDiscount() {
        int totalBonusPrice = getTotalBonusPrice();

        System.out.println(PROMOTION_DISCOUNT_MESSAGE + formatPrice(totalBonusPrice));
    }

    private void printMembershipDiscount() {
        int membershipDiscount = getMembershipDiscount();
        System.out.println(MEMBERSHIP_DISCOUNT_MESSAGE + formatPrice(membershipDiscount));
    }

    private void printCost() {
        int cost = getTotalPrice() - getTotalBonusPrice() - getMembershipDiscount();
        System.out.println(COST_MESSAGE + formatPrice(cost));
    }

    private int getTotalQuantity() {
        return items.stream()
            .mapToInt(Item::getQuantity)
            .sum();
    }

    private int getTotalPrice() {
        return items.stream()
            .mapToInt(Item::getTotalPrice)
            .sum();
    }

    private int getTotalBonusPrice() {
        return items.stream()
            .mapToInt(Item::getBonusPrice)
            .sum();
    }

    private int getMembershipDiscount() {
        return membershipCalculator.calculateMembershipDiscount(items);
    }

    private String formatPrice(int price) {
        return NumberFormat.getNumberInstance().format(price);
    }
}
