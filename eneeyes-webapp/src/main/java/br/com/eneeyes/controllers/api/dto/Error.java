package br.com.eneeyes.controllers.api.dto;

/**
 * Created by asus on 25/09/14.
 */
public class Error extends Result<Boolean> {
    public Error() {
        setValue(false);
    }

    public Error(String code, String message) {
        super(code, message);
        setValue(false);
    }

    @Override
    public String toString() {
        return "Error{" +
                "value=" + getValue() +
                ", code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
