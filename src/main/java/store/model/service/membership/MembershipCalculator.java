package store.model.service.membership;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.MembershipDiscountType;
import store.model.service.decision.DecisionInputService;

public class MembershipCalculator {
    private final DecisionInputService decisionInputService;

    public MembershipCalculator() {
        this.decisionInputService = new DecisionInputService();
    }

    public int calculateMembershipDiscount(List<Item> items) {
        String opinion = decisionInputService.getMembershipDiscountDecision();
        int totalRegularPrice = calculateTotalRegularPrice(items);
        return calculateBasedOnDecision(opinion, totalRegularPrice);
    }

    private int calculateTotalRegularPrice(List<Item> items) {
        return items.stream()
            .mapToInt(Item::getRegularPrice)
            .sum();
    }

    private int calculateBasedOnDecision(String opinion, int price) {
        if (opinion.equals("Y")) {
            return MembershipDiscountType.MEMBER.calculateDiscount(price);
        }
        return 0;
    }
}
