package store.util.validator;

import java.util.Arrays;
import store.util.message.ErrorMessage;

public class ItemFormValidator {

    public void validateForm(String input) {
        isEmptyString(input);
        hasSquareBrackets(input);
        containsHyphen(input);
        hasSingleHyphen(input);
    }

    private void isEmptyString(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM_ERROR);
        }
    }

    private void hasSquareBrackets(String input) {
        if (!input.startsWith("[")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM_ERROR);
        }
        if (!input.endsWith("]")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM_ERROR);
        }
    }

    private void containsHyphen(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM_ERROR);
        }
    }

    private void hasSingleHyphen(String input) {
        long count = Arrays.stream(input.split(""))
            .filter(s -> s.equals("-"))
            .count();

        if (count > 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM_ERROR);
        }
    }
}
