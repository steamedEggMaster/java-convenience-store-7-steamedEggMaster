package store.model.service.promotion;

import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.service.promotion.decision.DecisionInputService;
import store.model.service.promotion.decision.DecisionActionService;

public class PromotionDecisionHandler {
    private final DecisionInputService decisionInputService;
    private final DecisionActionService confirmActionService;
    private static final String YES = "Y";
    private Product product;
    private Promotion promotion;
    private Item item;

    public PromotionDecisionHandler() {
        this.decisionInputService = new DecisionInputService();
        this.confirmActionService = new DecisionActionService();
    }

    public void applyBasedOnRemainingQuantity(Product product, Promotion promotion, Item item) {
        setFields(product, promotion, item);
        if (item.getRemainingQuantity() < product.getInventory()) {
            applyWhenLessThanInventory();
            return;
        }
        applyIfSufficientInventory();
    }

    private void setFields(Product product, Promotion promotion, Item item) {
        this.product = product;
        this.promotion = promotion;
        this.item = item;
    }

    private void applyWhenLessThanInventory() {
        if (item.getRemainingQuantity() > 0 && item.getRemainingQuantity() < promotion.getBuy()) {
            String opinion = decisionInputService.getNoDiscountPurchaseDecision(item);
            applyNoDiscountDecision(opinion);
        }
        if (item.getRemainingQuantity() == promotion.getBuy()) {
            String opinion = decisionInputService.getBonusDecision(item, promotion);
            applyBonusDecision(opinion);
        }
    }

    private void applyIfSufficientInventory() {
        String opinion = decisionInputService.getNoDiscountPurchaseDecision(item);
        applyNoDiscountDecision(opinion);
    }

    private void applyNoDiscountDecision(String opinion) {
        if (opinion.equals(YES)) {
            confirmActionService.applyNoDiscountPurchase(product, item);
            return;
        }
        confirmActionService.decreaseQuantity(item);
    }

    private void applyBonusDecision(String opinion) {
        if (opinion.equals(YES)) {
            confirmActionService.applyNoDiscountPurchase(product, item);
            confirmActionService.applyBonus(product, promotion, item);
            return;
        }
        confirmActionService.applyNoDiscountPurchase(product, item);
    }
}
