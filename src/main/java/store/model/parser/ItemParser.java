package store.model.parser;

import store.model.domain.Item;
import store.util.message.ErrorMessage;
import store.util.validator.item.ItemFormValidator;

public class ItemParser {
    private final ItemFormValidator itemFormValidator;

    public ItemParser() {
        this.itemFormValidator = new ItemFormValidator();
    }

    public Item parse(String input) {
        itemFormValidator.validateForm(input);

        String name = parseName(input);
        int quantity = parseQuantity(input);

        return new Item(name, quantity);
    }

    private String parseName(String input) {
        int openBracket = 1;
        int hyphen = input.indexOf("-");
        return input.substring(openBracket, hyphen);
    }

    private int parseQuantity(String input) {
        int hyphen = input.indexOf("-") + 1;
        int closeBracket = input.length() - 1;
        String quantity = input.substring(hyphen, closeBracket);

        return stringToInt(quantity);
    }

    private int stringToInt(String input) {
        try {
            int quantity = Integer.parseInt(input);
            validatePositive(quantity);
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_ERROR);
        }
    }

    private void validatePositive(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_ERROR);
        }
    }
}
