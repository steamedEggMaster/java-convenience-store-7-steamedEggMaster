package store.model.parser;

import store.util.validator.OpinionValidator;

public class OpinionParser {
    private final OpinionValidator opinionValidator;

    public OpinionParser() {
        this.opinionValidator = new OpinionValidator();
    }

    public String parse(String input) {
        opinionValidator.validateYesOrNo(input);
        return input;
    }
}
