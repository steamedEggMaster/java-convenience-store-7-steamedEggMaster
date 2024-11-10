package store.util.validator;

import store.util.message.ErrorMessage;

public class OpinionValidator {

    public void validateYesOrNo(String input) {
        if (!input.equals("Y") && !input.equals("N")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_ERROR);
        }
    }
}
