package store.controller;

import java.util.List;
import store.model.domain.Item;
import store.model.domain.Product;
import store.model.domain.Promotion;
import store.model.loader.ItemLoader;
import store.model.loader.file.FileLoader;
import store.model.service.decision.DecisionInputService;
import store.model.service.membership.MembershipService;
import store.model.service.promotion.PromotionService;
import store.model.service.regular.RegularService;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final FileLoader fileLoader;
    private final InputView inputView;
    private final OutputView outputView;
    private final ItemLoader itemLoader;
    private final PromotionService promotionService;
    private final RegularService regularService;
    private final MembershipService membershipService;
    private final DecisionInputService decisionInputService;
    private List<Promotion> promotions;
    private List<Product> products;
    private List<Item> items;
    private int membershipDiscount;
    private String opinion;


    public StoreController() {
        this.fileLoader = new FileLoader();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.itemLoader = new ItemLoader();
        this.promotionService = new PromotionService();
        this.regularService = new RegularService();
        this.membershipService = new MembershipService();
        this.decisionInputService = new DecisionInputService();
    }

    public void run() {
        loadFile();
        startStoreLoop();
    }

    private void loadFile() {
        fileLoader.loadFile();
        promotions = fileLoader.getPromotions();
        products = fileLoader.getProducts();
    }

    private void startStoreLoop() {
        do {
            purchase();
            calculateItems();
            calculateMembershipDiscount();
            printReceipt();
            confirmAdditionalPurchaseOpinion();
        } while (!opinion.equals("N"));
    }

    private void purchase() {
        outputView.printProducts(products);
        items = loadItems();
    }

    private void calculateItems() {
        promotionService.applyPromotions(products, promotions, items);
        regularService.calculateRegularPrice(products, items);
    }

    private void calculateMembershipDiscount() {
        membershipDiscount = membershipService.calculateMembershipDiscount(items);
    }

    private void printReceipt() {
        outputView.printReceipt(items, membershipDiscount);
    }

    private void confirmAdditionalPurchaseOpinion() {
        opinion = decisionInputService.getAdditionalPurchaseDecision();
    }

    public List<Item> loadItems() {
        while (true) {
            try {
                String input = inputView.readItem();
                return itemLoader.loadItems(input, products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
