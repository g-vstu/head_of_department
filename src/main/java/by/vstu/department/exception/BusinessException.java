package by.vstu.department.exception;

public class BusinessException extends NamedException {

    public BusinessException(String message) {
        super(message, BusinessException.class);
    }
}
