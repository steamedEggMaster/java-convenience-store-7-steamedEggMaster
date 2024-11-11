package store.model.service.decision;

import store.model.domain.Item;
import store.model.domain.Promotion;
import store.model.parser.OpinionParser;
import store.view.InputView;

public class DecisionInputService {
    private final InputView inputView;
    private final OpinionParser opinionParser;

    public DecisionInputService() {
        this.inputView = new InputView();
        this.opinionParser = new OpinionParser();
    }

    public String getNoDiscountPurchaseDecision(Item item) {
        while (true) {
            try {
                String input = inputView.confirmNoDiscountPurchase(
                    item.getName(), item.getRemainingQuantity());
                return opinionParser.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getBonusDecision(Item item, Promotion promotion) {
        while (true) {
            try {
                String input = inputView.confirmBonus(item.getName(), promotion.getGet());
                return opinionParser.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getMembershipDiscountDecision() {
        while (true) {
            try {
                String input = inputView.confirmMembershipDiscount();
                return opinionParser.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
