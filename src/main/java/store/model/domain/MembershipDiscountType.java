package store.model.domain;

public enum MembershipDiscountType {
    MEMBER(0.3, 8000);

    private final double discountRate;
    private final int maxDiscount;

    MembershipDiscountType(double discountRate, int maxDiscount) {
        this.discountRate = discountRate;
        this.maxDiscount = maxDiscount;
    }

    public int calculateDiscount(int price) {
        int discount = (int) (price * discountRate);
        return Math.min(discount, maxDiscount);
    }
}
