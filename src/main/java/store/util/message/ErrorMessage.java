package store.util.message;

public class ErrorMessage {
    public static final String PREFIX = "[ERROR] ";
    public static final String SUFFIX = " 다시 입력해 주세요.";
    public static final String INVALID_FORM_ERROR = PREFIX + "올바르지 않은 형식으로 입력했습니다." + SUFFIX;
    public static final String INVALID_PRODUCT_ERROR = PREFIX + "존재하지 않는 상품입니다." + SUFFIX;
    public static final String QUANTITY_EXCEEDED_ERROR = PREFIX + "재고 수량을 초과하여 구매할 수 없습니다." + SUFFIX;
    public static final String INVALID_INPUT_ERROR = PREFIX + "잘못된 입력입니다." + SUFFIX;
}
