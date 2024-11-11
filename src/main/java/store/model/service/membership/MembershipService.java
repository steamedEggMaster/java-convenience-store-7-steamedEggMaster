package store.model.service.membership;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.MembershipDiscountType;
import store.model.service.decision.DecisionInputService;

public class MembershipService {
    private final DecisionInputService decisionInputService;

    public MembershipService() {
        this.decisionInputService = new DecisionInputService();
    }

    public int calculateMembershipDiscount(List<Item> items) {
        String opinion = decisionInputService.getMembershipDiscountDecision();
        int totalRegularPrice = getTotalRegularPrice(items);
        return calculateBasedOnDecision(opinion, totalRegularPrice);
    }

    private int getTotalRegularPrice(List<Item> items) {
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
